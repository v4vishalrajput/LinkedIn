import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ConnectionRequest } from 'src/app/dto/ConnectionRequest';
import { Post } from 'src/app/dto/Post';
import { User } from 'src/app/dto/user';
import { UserDataService } from 'src/app/service/user-data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  profileData! : User;
  liked : boolean = false;
  userid! : any; 
  userdata! : any;
  requestList! : User[]
  content! : any;
  imageUrl ! : any;
  formdata!:FormData;
  postData! : Post;
  allPosts! : Post[];
  allUsers! : User[];
  currDate! : Date;


   constructor(private service : UserDataService, private router : Router){
    this.profileData=new User();
    this.postData=new Post;
    this.requestList=[];
    this.allPosts=[];
    this.allUsers=[];
    // this.currDate=Date.

   }

   ngOnInit(){
    
  this.userid = localStorage.getItem('emailId')
  
  this.getFriendRequest();
  this.getMyProfile();
   this.getPosts();
  
   }

  likedClicked(){
     this.liked=this.liked?false : true;
  }

  dataToModel(u : User){
    this.userdata=u;
 }

 acceptReq(otherId : string){
  const connectReq=new ConnectionRequest(otherId,localStorage.getItem('emailId'));
 
  this.service.acceptRequest(connectReq).subscribe(data=>{
    this.getFriendRequest();
  });
}

rejectReq(otherId : string){
  const connectReq=new ConnectionRequest(otherId,localStorage.getItem('emailId'));
     
  this.service.rejectRequest(connectReq).subscribe(data=>{
    this.getFriendRequest();
  });

}

getPosts(){
  this.service.getAllPosts().subscribe(data=> {
    this.allPosts=data.postList;
    this.allUsers=data.userList;
  });
}

onImageChange(event: Event): void {

  const fileInput = event.target as HTMLInputElement;
  const file = (fileInput.files as FileList)[0];
  this.formdata=new FormData();
  this.formdata.append('file',file);
  console.log(this.formdata)
}

post(){
   this.service.getMediaUrl(this.formdata).subscribe(data=>{
     
    this.imageUrl=data[0];
    console.log(this.imageUrl);
    
    let myPost=new Post();
    myPost.emailId=this.userid;
    myPost.content=this.content;
    myPost.imageUrl=this.imageUrl;
    console.log(myPost);
    
    this.service.savePost(myPost).subscribe(d=>{ this.postData=d;
      console.log(d);
        this.getPosts();
        this.content='';
    })
    
   })
}


changeProfilePic(event : Event){
  this.onImageChange(event)
  this.service.getMediaUrl(this.formdata).subscribe(data=> {
    let url=data[0];
    console.log(url);
    
    this.service.changeProfilePic(this.userid,url).subscribe(data=>{
        this.getMyProfile();
        this.getPosts();
    })
  })
}

getMyProfile(){
  this.service.getData(this.userid).subscribe(data=> {
    this.profileData=data
    console.log(this.profileData);
    
  });

  
}

getFriendRequest(){
  this.service.getFriendRequests(this.userid ).subscribe(data=> this.requestList=data);
}
  
}
