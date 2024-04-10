package com.content.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.content.dto.CombinedDTO;
import com.content.dto.PostDTO;
import com.content.dto.UserDTO;
import com.content.entity.PostEntity;
import com.content.entity.SequenceId;
import com.content.exception.UserException;
import com.content.repository.ContentRepository;
import com.content.utility.Transformations;

@Service
@Transactional
public class ContentServiceImpl implements ContentService {

    private static final Log LOGGER = LogFactory.getLog(ContentServiceImpl.class);

    @Autowired
    private ContentRepository repository;

    @Autowired
    private MongoOperations mongoOperation;
    
    @Autowired
    private Environment env;

    private static final String HOSTING_SEQ_KEY = "hosting";

    @Override
    public List<PostDTO> getByEmailId(String emailId) {
        // TODO Auto-generated method stub
        List<PostEntity> entityList = repository.findAllByEmailId(emailId);

        return entityList.stream().map((entity) -> Transformations.PostEntitytoDto(entity)).toList();
    }
    
    private int dateCompare(LocalDateTime a, LocalDateTime b) {
        if(b.isAfter(a)) return 1;
        return -1;
    }

    @Override
    public CombinedDTO getAllPosts() {
        List<PostEntity> entityList = repository.findAll();
        Collections.sort(entityList,(a,b)->{
            return dateCompare(a.getDate(), b.getDate());
        });
        
        WebClient client=WebClient.create();
        List<PostDTO> postList=new ArrayList<>();
        List<UserDTO> userList=new ArrayList();
        
        for(PostEntity ent : entityList) {
            UserDTO udto=client.get().uri("http://localhost:2000/user/profile/"+ent.getEmailId()).retrieve().bodyToMono(UserDTO.class).block();
            userList.add(udto);
            postList.add(Transformations.PostEntitytoDto(ent));
        }
        
        return new CombinedDTO(postList,userList);
    }

    @Override
    public PostDTO savePost(PostDTO post) throws UserException {
        LOGGER.info("In save post with dto: " + post);
        PostEntity entity = Transformations.PostDTOtoEntity(post);
        entity.setDate(LocalDateTime.now());
        entity.setId(getNextSequenceId(HOSTING_SEQ_KEY));
        entity.setLikes(0);
        entity.setPeopleLiked(new ArrayList());
        entity = repository.save(entity);

        return Transformations.PostEntitytoDto(entity);
    }
    
    @Override
    public Boolean likedDislike(Long id, String doneBy) throws UserException{
        Optional<PostEntity> opt =  repository.findById(id);
        PostEntity entity= opt.orElseThrow(()->new UserException("Service.POST_NOT_FOUND"));
        List<String> peopleLikedList=entity.getPeopleLiked();
        if(peopleLikedList.contains(doneBy)) {
            peopleLikedList.remove(doneBy);
             entity.setPeopleLiked(peopleLikedList);
             repository.save(entity);
             return false;
            
        }else {
       
        peopleLikedList.add(doneBy);
     entity.setPeopleLiked(peopleLikedList);
     repository.save(entity);
     return true;
        }
    }

    public long getNextSequenceId(String key) throws UserException {
        Query query = new Query(Criteria.where("_id").is(key));
        Update update = new Update();
        update.inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        SequenceId seqId = mongoOperation.findAndModify(query, update, options, SequenceId.class);
        if (seqId == null) {
            throw new UserException("Unable to get sequence id for key : " + key);
        }
        return seqId.getSeq();
    }

    @Override
     public String saveMedia(MultipartFile file) throws IOException {
        String fileName = Math.abs(Math.random()*1000) +file.getOriginalFilename();
        String path = env.getProperty("storage.path")+ fileName;
        
        InputStream is = file.getInputStream();

        OutputStream os = Files.newOutputStream(Path.of(path));
        
        long len = is.available();
        int megaByte = 1024 * 1024;
        for (int i = 0; i < len / megaByte; i++) {
            os.write(is.readNBytes(megaByte));
        }
        os.write(is.readNBytes((int) len % megaByte));

        os.close();
        is.close();

        

        return "assets\\uploadedImages\\"+fileName;
    }

}

