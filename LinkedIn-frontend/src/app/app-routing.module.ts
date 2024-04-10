import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './auth/signup/signup.component';
import { LoginComponent } from './auth/login/login.component';
// import { FeedComponent } from './comp/feed/feed.component';
import { NetworkComponent } from './comp/network/network.component';
import { HomeComponent } from './comp/home/home.component';
import { EditProfileComponent } from './comp/edit-profile/edit-profile.component';
import { MainComponent } from './comp/main/main.component';
import { ProfileComponent } from './comp/profile/profile.component';


const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'main', component: MainComponent},
  {path:'editprofile' , component: EditProfileComponent},
  {path:'profile', component: ProfileComponent},
  {path:'signup', component: SignupComponent},
  {path: 'login', component: LoginComponent},
  // {path: 'feed', component: FeedComponent},
  {path: 'network', component: NetworkComponent},
  {path: '', redirectTo: 'main', pathMatch: 'full'},
  { path: 'jobs', loadChildren: () => import('./jobs/jobs.module').then(m => m.JobsModule) },
  {path: '**', redirectTo: 'main', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
