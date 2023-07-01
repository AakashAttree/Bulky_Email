import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { EmailComponent } from './email/email.component';
import { UserDataComponent } from './user-data/user-data.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCommonModule, MatNativeDateModule, MatOptionModule } from '@angular/material/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { OnceOptionsComponent } from './schedule/once-options/once-options.component';
import { DailyOptionsComponent } from './schedule/daily-options/daily-options.component';
import { WeeklyOptionsComponent } from './schedule/weekly-options/weekly-options.component';
import { MonthlyOptionsComponent } from './schedule/monthly-options/monthly-options.component';
import { ReactiveFormsModule } from '@angular/forms';
import { TimeComponent } from './schedule/time/time.component';
import { HttpClientModule } from '@angular/common/http';
import { ShowEmailsComponent } from './show-emails/show-emails.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { AboutmeComponent } from './aboutme/aboutme.component';

@NgModule({
  declarations: [
    AppComponent,
    ScheduleComponent,
    EmailComponent,
    UserDataComponent,
    OnceOptionsComponent,
    DailyOptionsComponent,
    WeeklyOptionsComponent,
    MonthlyOptionsComponent,
    TimeComponent,
    ShowEmailsComponent,
    AboutmeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatCommonModule,
    MatOptionModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatListModule,
    MatInputModule,
    MatSelectModule,
    MatSidenavModule,
    MatCardModule,
    MatTableModule,
    MatNativeDateModule,
    HttpClientModule,
    MatPaginatorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
