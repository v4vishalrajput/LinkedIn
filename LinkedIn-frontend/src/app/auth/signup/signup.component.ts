import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/dto/user';
import { UserDataService } from 'src/app/service/user-data.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  signupForm! : FormGroup;
  successMessage: any;
  errorMessage: any;
  constructor(private fb : FormBuilder,private router : Router, private userService : UserDataService){
  }

  ngOnInit() : void { 
    this.signupForm=this.fb.group({
      firstName : ['',[Validators.required, Validators.pattern("[A-Z][a-z]*")]],
      lastName : ['',[Validators.required, Validators.pattern("[A-Z][a-z]*")]],
      phoneNo : ['',[Validators.required,Validators.pattern("[1-9][0-9]{9}")]],
      email : ['',[Validators.required, Validators.pattern("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|org|in)$")]],
      password : ['', [Validators.required,Validators.pattern("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,16}$")]],
      confirmPassword : ['',  [Validators.required,Validators.pattern("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,16}$")]]
    })
  }

  public register(){
     this.errorMessage=null;
    //  console.log(this.signupForm)
     this.userService.register(this.signupForm.value).subscribe(data=>{
      localStorage.setItem("emailId",data.email);
      this.userService.userData=data;
      // console.log(this.userService.userData);
      
      this.router.navigate(['/editprofile']);
     }, error=> this.errorMessage=error.error.errorMessage)
  }
}
