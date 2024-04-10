import { Component } from '@angular/core';
import { ConnectionRequest } from 'src/app/dto/ConnectionRequest';
import { User } from 'src/app/dto/user';
import { UserDataService } from 'src/app/service/user-data.service';

@Component({
  selector: 'app-network',
  templateUrl: './network.component.html',
  styleUrls: ['./network.component.css']
})
export class NetworkComponent {
userdata! : any;
userList! : User []
requestList! : User[]
 userid! : any;
 suggestionList ! : User[]
 profileData! : User;

constructor(private service : UserDataService){}

ngOnInit(){
  this.userList=[];
  this.requestList=[];
  this.suggestionList=[];
  this.profileData=new User();

  this.userid = localStorage.getItem('emailId')
  this.service.getData(this.userid).subscribe(data=> this.profileData=data);
  this.service.getConnections(this.userid ).subscribe(data=> {
    console.log(data.length);
    this.userList=data
  });
  this.service.suggestions(this.userid ).subscribe(data=> this.suggestionList=data);
  this.service.getFriendRequests(this.userid ).subscribe(data=> this.requestList=data);
}

  dataToModel(u : User){
     this.userdata=u;
  }

  acceptReq(otherId : string){
    const connectReq=new ConnectionRequest(otherId,localStorage.getItem('emailId'));
   
    this.service.acceptRequest(connectReq).subscribe(data=>{
      this.service.getConnections(this.userid ).subscribe(data=> this.userList=data);
      this.service.suggestions(this.userid ).subscribe(data=> this.suggestionList=data);
      this.service.getFriendRequests(this.userid ).subscribe(data=> this.requestList=data);
    });
  }

  rejectReq(otherId : string){
    const connectReq=new ConnectionRequest(otherId,localStorage.getItem('emailId'));
       
    this.service.rejectRequest(connectReq).subscribe(data=>{
     
      this.service.suggestions(this.userid ).subscribe(data=> this.suggestionList=data);
      this.service.getFriendRequests(this.userid ).subscribe(data=> this.requestList=data);

    });
  
}

removeConnection(otherId : string){
  const connectReq=new ConnectionRequest(otherId,localStorage.getItem('emailId'));
       
  this.service.removeConnection(connectReq).subscribe(data=>{
    this.service.getConnections(this.userid ).subscribe(data=> this.userList=data);
    this.service.suggestions(this.userid ).subscribe(data=> this.suggestionList=data);
  });
}

sendReq(otherId : string){
  const connectReq=new ConnectionRequest(localStorage.getItem('emailId'),otherId);
  this.service.sendRequest(connectReq).subscribe(data=>{
    
    this.service.suggestions(this.userid ).subscribe(data=> this.suggestionList=data);
   
  });
}

getData(){
  this.service.getData(this.userid).subscribe(data=> this.profileData=data);
}

}



