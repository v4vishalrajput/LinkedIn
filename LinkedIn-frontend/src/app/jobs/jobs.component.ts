import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserDataService } from '../service/user-data.service';
import { Job } from '../dto/Job';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.css']
})
export class JobsComponent {

  allJobs!: Job[]
  job! : Job; 
  searchValue! : any;
  userid ! : any;

  constructor(private router : Router , private service : UserDataService){
    this.userid=localStorage.getItem('emailId');
  }

  ngOnInit(){
    this.getAllJobs();
  }

  getAllJobs(){
    this.service.getAllJobs().subscribe(data=>{
      this.allJobs=data;
     })
  }

  logout(){
    localStorage.clear();
    this.router.navigate(['/main'])
  }

  dataToModel(j: Job){
    this.job=j;
    // const modalElement = document.getElementById('editUserModal');
    // const modal = Modal.getOrCreateInstance(modalElement);
    // modal.show();
  }

  onSearch(){
      
      if(this.searchValue) this.service.getJobsLike(this.searchValue).subscribe(data=> this.allJobs=data);
      else  this.service.getAllJobs().subscribe(data=>{
        this.allJobs=data;
       })
  }

  getAppliedJobs(){
     this.service.getAppliedJobs(this.userid).subscribe(data=>{
      this.allJobs=data;
     })
  }
}
