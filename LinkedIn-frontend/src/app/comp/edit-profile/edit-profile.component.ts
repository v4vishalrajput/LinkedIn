import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { User } from 'src/app/dto/user';
import { UserDataService } from 'src/app/service/user-data.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent {
   userId! : any;
  signupForm! : FormGroup;
  profileData! : User;
  successMessage: any;
  errorMessage: any;
  constructor(private fb : FormBuilder,private router : Router, private userService : UserDataService, private route : ActivatedRoute){
  }

  ngOnInit() : void { 
    

    this.signupForm=this.fb.group({
      firstName : ['',[Validators.required, Validators.pattern("[A-Z][a-z]*")]],
      lastName : ['',[Validators.required, Validators.pattern("[A-Z][a-z]*")]],
      phoneNo : ['',[Validators.required,Validators.pattern("[1-9][0-9]{9}")]],
      password : ['', [Validators.required,Validators.pattern("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,16}$")]],
      confirmPassword : ['',  [Validators.required,Validators.pattern("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,16}$")]],
      headLine : [''],
      jobTitle : [''],
      location : [''],
      education : this.fb.group({
        institution : [''],
        degree : [''],
        fieldOfStudy : ['']
      }),
      summary : this.fb.group({
        skill : [''],
        experience : [''],
        aspiration : ['']
      })
    })
    
    this.userId=localStorage.getItem('emailId');
    
    this.userService.getData(this.userId).subscribe(data=>{
      this.profileData=data;

      console.log(this.profileData)
       
      this.signupForm.controls['firstName'].setValue(this.profileData.firstName);
      this.signupForm.controls['lastName'].setValue(this.profileData.lastName);
      this.signupForm.controls['password'].setValue(this.profileData.password);
      this.signupForm.controls['confirmPassword'].setValue(this.profileData.password);
      this.signupForm.controls['phoneNo'].setValue(this.profileData.phoneNo);
      this.signupForm.controls['headLine'].setValue(this.profileData.headLine);
      this.signupForm.controls['jobTitle'].setValue(this.profileData.jobTitle);
      this.signupForm.controls['location'].setValue(this.profileData.location);
     let edu = <FormGroup>this.signupForm.controls['education']
       edu.controls['degree'].setValue(this.profileData.education.degree);
       edu.controls['institution'].setValue(this.profileData.education.institution);
       edu.controls['fieldOfStudy'].setValue(this.profileData.education.fieldOfStudy);
       let sum = <FormGroup>this.signupForm.controls['summary']
       sum.controls['aspiration'].setValue(this.profileData.summary.aspiration);
       sum.controls['experience'].setValue(this.profileData.summary.experience);
       sum.controls['skill'].setValue(this.profileData.summary.skill);
      
  

    } ,error=> this.errorMessage=error.error.errorMessage)
    
    
  }
    update(){
      this.errorMessage=null;
    //  let a=<FormGroup>this.signupForm.controls['education'];
    
    // console.log(a.controls["degree"]);
    this.userService.createProfile(this.userId,this.signupForm.value).subscribe(data=>{
      this.profileData=data;
      console.log(this.profileData);
      this.router.navigate(['/home'])
    }, error=>{
      this.errorMessage=error.error.errorMessage;
    })
    
  }
}
