import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'LinkedIn';
  constructor(private router : Router){}
  ngOnInit(){
    let id : any = localStorage.getItem('emailId');
    if(id==null){
       this.router.navigate(['/main'])
    }
  }

}
