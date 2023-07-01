import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormArray, FormGroup, FormGroupDirective } from '@angular/forms';

@Component({
  selector: 'app-daily-options',
  templateUrl: './daily-options.component.html',
  styleUrls: ['./daily-options.component.css']
})
export class DailyOptionsComponent implements OnInit {

  form!: FormGroup
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
