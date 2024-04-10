import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/dto/user';
import { UserDataService } from 'src/app/service/user-data.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  profileData! : User;
  userid! : any;
  constructor(private service : UserDataService, private router : Router){}
  ngOnInit(){
    
    this.userid = localStorage.getItem('emailId')
    this.service.getData(this.userid).subscribe(data=> this.profileData=data);
     }
}
