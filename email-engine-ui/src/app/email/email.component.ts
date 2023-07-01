import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Email } from './email';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.css']
})
export class EmailComponent implements OnInit {
  emailFormGroup!: FormGroup;
  emailId!:string | null
  email:Email = new Email();
  constructor(private router:Router
    , private httpClient:HttpClient
    , private formBuilder:FormBuilder
    ,private ativatedroute:ActivatedRoute) {
      
     }

  ngOnInit(): void {
    this.emailId = this.ativatedroute.snapshot.paramMap.get("id");
    this.ativatedroute.params.subscribe(param => { 
      if(param != null){
        this.emailId = param.get('id');
      }
      
  });
  this.emailFormGroup = new FormGroup({
    emailname: new FormControl(null,Validators.required),
    emailsubject: new FormControl(null,Validators.required),
    emailbody: new FormControl(null,Validators.required)
  });


  if(this.emailId != null){
    this.httpClient.get<Email>("http://localhost:8080/get/email/"+this.emailId).subscribe((data: Email)=>{
    console.log(data)  
    this.email = data
    this.emailFormGroup.get("emailname")?.setValue(this.email.emailname);
    this.emailFormGroup.get("emailsubject")?.setValue(this.email.emailsubject);
    this.emailFormGroup.get("emailbody")?.setValue(this.email.emailbody);
    },
    error=>{
      console.log(error)
    });
  }
  
  }

  onNextStep(){
    console.log(this.emailFormGroup.value);
    this.emailFormGroup.value.id= this.emailId;
    console.log(this.emailFormGroup.value);
    this.httpClient.post<Email>("http://localhost:8080/save/email",this.emailFormGroup.value).subscribe((data: Email)=>{
      this.router.navigate(["/schedule/"+data.id]);
    },
    error=>{
      console.log(error)
    });
   
  }

}
