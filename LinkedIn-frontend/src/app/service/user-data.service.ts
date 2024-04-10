import { Injectable } from '@angular/core';
import { User } from '../dto/user';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Job } from '../dto/Job';
import { ConnectionRequest } from '../dto/ConnectionRequest';
import { Post } from '../dto/Post';
import { Combined } from '../dto/Combined';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {
 userData ! : User

 url : string = 'http://localhost:2000';
  constructor(private http : HttpClient) { }

  login(data : any) : Observable<User>{
    console.log(data);
      return this.http.post<User>(this.url + '/user/validate', data);
  }

  register(data : any) :  Observable<User>{
    
    return this.http.post<User>(this.url+ '/user/register',data);
    
  }

  createProfile(id: string, data : any) : Observable<User>{
    console.log(data);
    
    return this.http.put<User>('http://localhost:2000'+'/user/profile/'+id,data);
  }

  reset(data : any) : Observable<User>{
    
    return this.http.put<User>(this.url+'/user/reset',data);
  }
  
  getData(data : string) : Observable<User>{
    
    return this.http.get<User>(this.url+'/user/profile/'+data);
  }

  getAllJobs() : Observable<Job []>{
    return this.http.get<Job []>(this.url+'/job/get');
  }

  getJobById(jobId : string) : Observable<Job>{
    return this.http.get<Job>(this.url+'/job/getById/'+jobId)
  }

  getJobsLike(data : any) : Observable<Job []>{
    return this.http.get<Job []>(this.url+'/job/get/'+data);
  }

  getConnections(data : any) : Observable<User[]>{
     return this.http.get<User[]>(this.url+'/network/getConnectionsDetails/'+data);
  }

  getFriendRequests(data : any) : Observable<User[]>{
    return this.http.get<User[]>(this.url+'/network/getRequestsDetails/'+data);
 }

 acceptRequest(data : ConnectionRequest) {
  
  return this.http.post<boolean>(this.url+'/network/acceptRequest',data);
 }

 rejectRequest(data : any) {
  return this.http.post<boolean>(this.url+'/network/rejectRequest',data);
 }

 removeConnection(data : any) {
  return this.http.post<boolean>(this.url+'/network/removeConnection',data);
 }

 suggestions(data : any) : Observable<User[]>{
  return this.http.get<User[]>(this.url+'/network/getNonConnectionsDetails/'+data); 
}

sendRequest(data : ConnectionRequest) {
  
  return this.http.post<boolean>(this.url+'/network/sendRequest',data);
 }

 
 savePost(data : any) : Observable<Post> {
   return this.http.post<Post>(this.url+'/posts',data);
 }

 getMediaUrl(formdata : FormData) : Observable<string[]>{
   return this.http.post<string[]>(this.url+'/posts/getMediaUrl',formdata);
 }

 getAllPosts() : Observable<Combined> {
  return this.http.get<Combined>(this.url+'/posts');
 }

 changeProfilePic(emailId : any,data : any) : Observable<Boolean> {
  console.log(data,emailId);
  return this.http.put<Boolean>(this.url+'/user/setProfileImage/'+emailId,data);
 }

 toggle(emailId : any, jobId : any) : Observable<boolean>{
  return this.http.get<boolean>(this.url+'/job/toggle/'+emailId+'/'+jobId);
 }

isApplied(emailId : any , jobId : any) : Observable<boolean>{
  return this.http.get<boolean>(this.url+'/job/isApplied/'+emailId+'/'+jobId);
}

getJob(jobId : any) : Observable<Job>{
  return this.http.get<Job>(this.url+'/job/getById/'+jobId);
}

getAppliedJobs(emailId : any) : Observable<Job[]>{
  return this.http.get<Job[]>(this.url+'/job/getAppliedJobs/'+emailId);
}

}