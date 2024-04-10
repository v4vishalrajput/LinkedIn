import { Component, Input } from '@angular/core';
import { User } from 'src/app/dto/user';

@Component({
  selector: 'app-profilemodal',
  templateUrl: './profilemodal.component.html',
  styleUrls: ['./profilemodal.component.css']
})
export class ProfilemodalComponent {
@Input() user ! : User
}
