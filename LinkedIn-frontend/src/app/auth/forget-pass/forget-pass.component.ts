import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserDataService } from 'src/app/service/user-data.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forget-pass',
  templateUrl: './forget-pass.component.html',
  styleUrls: ['./forget-pass.component.css']
})
export class ForgetPassComponent {
  resetForm! : FormGroup;
  successMessage: any;
  errorMessage: any;
  show! : boolean
  constructor(private fb : FormBuilder, private userService : UserDataService, private router : Router){
  
  }

  ngOnInit() : void {
    this.show=true;
    this.resetForm=this.fb.group({
   
      phoneNo : ['',[Validators.required,Validators.pattern("[1-9][0-9]{9}")]],
      email : ['',[Validators.required, Validators.pattern("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|org|in)$")]],
      password : ['', [Validators.required,Validators.pattern("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,16}$")]],
      confirmPassword : ['',  [Validators.required,Validators.pattern("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,16}$")]]
    })
  }

  public reset(){
   this.show=false
    this.successMessage=null;
    this.errorMessage=null;

    this.userService.reset(this.resetForm.value).subscribe(data=>{
       this.userService.userData=data;
       console.log(this.userService.userData);
       this.successMessage='Password has been updated successfully!'
      
     
     setTimeout(()=>{
      let element=<HTMLElement>document.getElementById('btn-close-ts');
      element.click();
     },1000)

    },error=>{
      this.errorMessage=error.error.errorMessage
    })
  }

  cancel() : any{
    window.location.reload();
  }
}
