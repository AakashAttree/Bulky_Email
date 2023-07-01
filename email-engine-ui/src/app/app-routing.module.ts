import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutmeComponent } from './aboutme/aboutme.component';
import { EmailComponent } from './email/email.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { ShowEmailsComponent } from './show-emails/show-emails.component';
import { UserDataComponent } from './user-data/user-data.component';

const routes: Routes = [
  {path:"aboutme",component:AboutmeComponent},
  {path:"schedule/:id",component:ScheduleComponent},
  {path:"email/:id",component:EmailComponent},
  {path:"email",component:EmailComponent},
  {path:"upload/:id",component:UserDataComponent},
  {path:"showemail",component:ShowEmailsComponent},
  {path:"",redirectTo:"/showemail",pathMatch:"full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
