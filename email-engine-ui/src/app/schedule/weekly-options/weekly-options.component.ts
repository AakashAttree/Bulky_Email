import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormArray, FormControl, FormGroup, FormGroupDirective } from '@angular/forms';

@Component({
  selector: 'app-weekly-options',
  templateUrl: './weekly-options.component.html',
  styleUrls: ['./weekly-options.component.css']
})
export class WeeklyOptionsComponent implements OnInit {
  form!: FormGroup
  toppings = new FormControl('');
  daysOfWeek=["Sunday","Monday","Tuesday","Wednesday","Thrusday","Friday","Saturday"]
  selectedDay:string=""
  constructor(private rootForm:FormGroupDirective) { 
    this.form = this.rootForm.control
  }

  ngOnInit(): void {
  }

  getSchedule(){
    return (<FormArray> this.form.get('schedules')).controls
  }

  getFormGroup(item:AbstractControl){
    return <FormGroup>item;
  }
}
