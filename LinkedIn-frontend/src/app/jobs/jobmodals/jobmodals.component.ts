import { Component, Input, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { Job } from 'src/app/dto/Job';
import { UserDataService } from 'src/app/service/user-data.service';

@Component({
  selector: 'app-jobmodals',
  templateUrl: './jobmodals.component.html',
  styleUrls: ['./jobmodals.component.css']
})
export class JobmodalsComponent {
@Input() job! : any;

errorMessage ! : any;
button! : any;
userId! : any;
disableApply! : boolean

constructor(private router : Router, private service :UserDataService){
  this.button='Apply'
  this.disableApply=false;

}

ngOnInit(){
  
this.userId=localStorage.getItem('emailId');
console.log(this.userId);

  

}

ngOnChanges(changes:SimpleChanges){
  if(changes['job']){
    this.service.isApplied(this.userId,this.job.jobId).subscribe(data=>{
      console.log(data);
      
      this.disableApply=data;
      this.shouldApply();
    })
  }
}

shouldApply(){
   if(this.disableApply) this.button='Withdraw'
   else this.button='Apply'
}

onPress(){
  this.service.toggle(this.userId,this.job.jobId).subscribe(data=>{
    this.disableApply=data;
    this.shouldApply();
  })
}

}
