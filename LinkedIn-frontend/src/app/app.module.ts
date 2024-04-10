import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ForgetPassComponent } from './auth/forget-pass/forget-pass.component';
// import { FeedComponent } from './comp/feed/feed.component';
import { NetworkComponent } from './comp/network/network.component';
import { NavbarComponent } from './comp/navbar/navbar.component';
import { HomeComponent } from './comp/home/home.component';
import { EditProfileComponent } from './comp/edit-profile/edit-profile.component';
import { MainComponent } from './comp/main/main.component';
import { ProfileComponent } from './comp/profile/profile.component';
import { ProfilemodalComponent } from './comp/profilemodal/profilemodal.component';





@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    ForgetPassComponent,
    // FeedComponent,
    NetworkComponent,
    NavbarComponent,
    HomeComponent,
    EditProfileComponent,
    MainComponent,
    ProfileComponent,
    ProfilemodalComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
   
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
