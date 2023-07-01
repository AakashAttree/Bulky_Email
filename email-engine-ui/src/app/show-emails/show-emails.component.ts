import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
export class EmailDetail {
  id!:number;
  emailname!:string;
  emailsubject!:string
  scheduled:boolean=false
  contactsCount!:number
  modify!:string
  constructor(newid:number,
    newemailname:string,
    newemailsubject:string,
    newscheduled:boolean,
    newcontactsCount:number)
    {
      this.id = newid;
      this.emailname = newemailname;
      this.emailsubject = newemailsubject;
      this.scheduled = newscheduled;
      this.contactsCount = newcontactsCount
    }
}

/** Constants used to fill up our data base. */

@Component({
  selector: 'app-show-emails',
  templateUrl: './show-emails.component.html',
  styleUrls: ['./show-emails.component.css']
})
export class ShowEmailsComponent implements OnInit {
  displayedColumns: string[] = ['id', 'emailname', 'emailsubject', 'scheduled','contactsCount','modify'];
  dataSource!: MatTableDataSource<EmailDetail>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;


  constructor(private httpClient:HttpClient,private router:Router) { 
    
  }

  ngOnInit(): void {
    this.httpClient.get<any>("http://localhost:8080/get/emails").subscribe((data: [{
      id:number,
      emailname:string
      emailsubject:string
      scheduled:boolean
      contactsCount:number
    }])=>{
      const emailDetails = createEmailDetails(data);

    // Assign the data to the data source for the table to render
    this.dataSource = new MatTableDataSource(emailDetails);
    });
  }
 
  openEdit(emailId:number){
    this.router.navigate(['/email/'+emailId]);
  }
  openEmail(){
    this.router.navigate(['/email']);
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}

/** Builds and returns a new User. */
function createEmailDetails(data: [{
  id:number,
  emailname:string
  emailsubject:string
  scheduled:boolean
  contactsCount:number
}]): EmailDetail[] {
  let array:EmailDetail[] = []; 
  data.forEach((value:{
    id:number,
    emailname:string
    emailsubject:string
    scheduled:boolean
    contactsCount:number
  }, index:number)=>{
    array.push(new EmailDetail(
      value.id,
      value.emailname,
      value.emailsubject,
      value.scheduled,
      value.contactsCount
      ));
  });
    return array;
}


