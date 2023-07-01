import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-data',
  templateUrl: './user-data.component.html',
  styleUrls: ['./user-data.component.css']
})
export class UserDataComponent implements OnInit {
  uploadForm!:FormGroup
  file!:File
  emailId!:string|null
  constructor(private router:Router
    , private httpClient:HttpClient
    
    ,private ativatedroute:ActivatedRoute) {
      
     }

  ngOnInit(): void {
    this.emailId = this.ativatedroute.snapshot.paramMap.get("id");
    this.ativatedroute.params.subscribe(param => { 
      if(param != null){
        this.emailId = param.get('id');
      }
      
  });
    this.uploadForm = new FormGroup({
      file:new FormControl(null)
    });
  }

  changeFile(event:any)
  {
    if(event.target.files.length > 0){
      this.file = event.target.files[0];
    }
  }

  saveData(){
    var fd = new FormData();
    fd.append('file', this.file);
    this.httpClient.post<any>("http://localhost:8080/upload/"+this.emailId,fd).subscribe((data: any)=>{
    console.log(data)  
    this.router.navigate(["/showemail"]);
    },
    error=>{
      console.log(error)
    });
  }
}
