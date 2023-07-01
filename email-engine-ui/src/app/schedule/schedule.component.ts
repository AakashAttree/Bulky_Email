import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Schedule } from './schedule';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {
  selected: Date | null = null;
  emailId!:string | null
  schedule:Schedule = new Schedule();
  scheduleForm!: FormGroup;
  constructor(private formBuilder:FormBuilder
    ,private ativatedroute:ActivatedRoute
    ,private httpClient:HttpClient
    ,private router:Router) { }

  ngOnInit(): void {
    this.emailId = this.ativatedroute.snapshot.paramMap.get("id");
    this.ativatedroute.params.subscribe(param => { 
      if(param != null){
        this.emailId = param.get('id');
      }
      
  });
  if(this.emailId != null){
    this.httpClient.get<Schedule>("http://localhost:8080/get/schedule/"+this.emailId).subscribe((data: Schedule)=>{
    console.log(data)  
    this.schedule = data
    },
    error=>{
      console.log(error)
    });
  }
    this.scheduleForm = new FormGroup({
      name: new FormControl(this.schedule.jobName,Validators.required),
      mode: new FormControl(this.schedule.mode,Validators.required),
      schedules: new FormArray([
        new FormGroup({
          date: new FormControl(this.schedule.date),
          dayOfWeek: new FormControl(this.schedule.dayOfWeek),
          month: new FormControl(this.schedule.month),
          dayOfMonth: new FormControl(this.schedule.dayOfMonth),
          time: new FormGroup({
            hour: new FormControl(this.schedule.hour,Validators.required),
            minute: new FormControl(this.schedule.minute,Validators.required),
            second: new FormControl(this.schedule.second,Validators.required)
          })
        })
      ])
    })
  }
  printForm(){
    console.log(this.scheduleForm.value)
  }
  onNextStep(){
    this.httpClient.post<any>("http://localhost:8080/schedule/"+this.emailId,this.scheduleForm.value).subscribe((data: any)=>{
      this.router.navigate(["/upload/"+this.emailId]);
    },
    error=>{
      console.log(error)
    });
   
  }

}
