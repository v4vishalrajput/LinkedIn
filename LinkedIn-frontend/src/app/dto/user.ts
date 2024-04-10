import { Education } from "./Education";
import { Summary } from "./Summary";

export class User{
    id!: number;
    firstName!: string;
    lastName!: string;
    email!: string;
    password!: string;
    phoneNo!: string;
    image!: string;
    headLine!: string;
    jobTitle!: string;
    location!: string;
    summary!: Summary;
    education!: Education;

}