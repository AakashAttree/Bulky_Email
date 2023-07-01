import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormArray, FormControl, FormGroup, FormGroupDirective } from '@angular/forms';

@Component({
  selector: 'app-monthly-options',
  templateUrl: './monthly-options.component.html',
  styleUrls: ['./monthly-options.component.css']
})
export class MonthlyOptionsComponent implements OnInit {
  toppings = new FormControl('');

  dates: number[] = [];
  months: string[] = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
  form!: FormGroup
  constructor(private rootForm:FormGroupDirective) { 
    this.form = this.rootForm.control
  }

  ngOnInit(): void {
    for(var index=0;index<=30;index++){
      this.dates[index]=index+1;
    }
  }
  getSchedule(){
    return (<FormArray> this.form.get('schedules')).controls
  }

  getFormGroup(item:AbstractControl){
    return <FormGroup>item;
  }
  

}
