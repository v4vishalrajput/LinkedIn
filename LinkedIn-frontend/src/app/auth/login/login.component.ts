import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserDataService } from 'src/app/service/user-data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm! : FormGroup;
  successMessage: any;
  errorMessage: any;

  constructor(private fb : FormBuilder, private userService : UserDataService, private router : Router){
  }

  ngOnInit() : void{
    this.loginForm=this.fb.group({
      email : ['',[Validators.required]],
      password : ['',[Validators.required]]
    })
  }

  public validate(){
    this.errorMessage=null;
    this.userService.login(this.loginForm.value).subscribe(data=>{
      localStorage.setItem("emailId",data.email);
      this.userService.userData=data;
      console.log(this.userService.userData)
       this.router.navigate(['/home'])
    }, error=>this.errorMessage=error.error.errorMessage
    )
  }


}

