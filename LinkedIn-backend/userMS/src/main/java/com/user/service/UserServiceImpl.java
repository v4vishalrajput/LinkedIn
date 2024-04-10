package com.user.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.dto.EducationDTO;
import com.user.dto.LoginDTO;
import com.user.dto.SummaryDTO;
import com.user.dto.UserDTO;
import com.user.entity.SequenceId;
import com.user.entity.User;
import com.user.exception.UserException;
import com.user.exception.UserNotFoundException;
import com.user.repository.UserRepository;
import com.user.utility.RestUtility;
import com.user.utility.Transformations;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static final Log LOGGER = LogFactory.getLog(UserServiceImpl.class);
	@Autowired
	private UserRepository repository;

	@Autowired
	private MongoOperations mongoOperation;

	@Autowired
	RestUtility restUtil;

	private static final String HOSTING_SEQ_KEY = "hosting";

	@Override
	public UserDTO findByEmail(String email) throws UserNotFoundException {

		User entity = this.findUserByEmailId(email);
		return Transformations.userEntityToDto(entity);
	}

	@Override
	public User findUserByEmailId(String email) throws UserNotFoundException {
		Optional<User> op = repository.findByEmail(email);

		User entity = op.orElseThrow(() -> new UserNotFoundException("Service.USERS_NOT_FOUND"));

		return entity;
	}

	@Override
	public UserDTO register(UserDTO dto) throws UserException {
		// TODO Auto-generated method stub
		Optional<User> op = repository.findByEmail(dto.getEmail());
		if (op.isPresent())
			throw new UserException("Service.USER_FOUND");
		LOGGER.info(op);
		dto.setId(getNextSequenceId(HOSTING_SEQ_KEY));
		dto.setImage("assets\\uploadedImages\\default.jpg");
		EducationDTO edu = new EducationDTO();
		edu.setDegree("");
		edu.setInstitution("");
		edu.setFieldOfStudy("");

		SummaryDTO sum = new SummaryDTO();
		sum.setAspiration("");
		sum.setExperience("");
		sum.setSkill("");
		dto.setEducation(edu);
		dto.setSummary(sum);
		User ent = Transformations.userDtoToEntity(dto);

		restUtil.createConnection(dto.getEmail());

		User entity = repository.save(ent);

		return Transformations.userEntityToDto(entity);
	}

	@Override
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
	public UserDTO validateUser(LoginDTO dto) throws UserException, UserNotFoundException {

		User entity = this.findUserByEmailId(dto.getEmail());
		if (!entity.getPassword().equals(dto.getPassword()))
			throw new UserException("Service.INVALID_CREDENTIALS");

		return Transformations.userEntityToDto(entity);

	}

	@Override
	public UserDTO resetPassword(LoginDTO dto) throws UserException, UserNotFoundException {

		User entity = this.findUserByEmailId(dto.getEmail());
		if (entity.getPhoneNo().equals(dto.getPhoneNo())) {

			entity.setPassword(dto.getPassword());
			entity = repository.save(entity);

		} else
			throw new UserException("Service.INVAID_PHONE");

		return Transformations.userEntityToDto(entity);
	}

	@Override
	public UserDTO profileCreate(String userId, UserDTO userDTO) throws UserNotFoundException {
		User user = findUserByEmailId(userId);
		LOGGER.info(user);

		userDTO.setEmail(userId);
		userDTO.setImage(user.getImage());
		userDTO.setId(user.getId());

		User updatedUser = repository.save(Transformations.userDtoToEntity(userDTO));
		LOGGER.info(updatedUser);
		return Transformations.userEntityToDto(updatedUser);
	}

	@Override
	public UserDTO getUserProfile(String emailId) throws UserNotFoundException {
		User user = findUserByEmailId(emailId);

		return Transformations.userEntityToDto(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> allEntity = repository.findAll();
		return allEntity.stream().map((u) -> Transformations.userEntityToDto(u)).toList();
	}

	@Override
	public boolean changeProfilePic(String imageUrl, String emailId) throws UserNotFoundException {
		User user = findUserByEmailId(emailId);
		user.setImage(imageUrl);
		repository.save(user);
		LOGGER.info("in changeProfilePic");
		return true;
	}
}
