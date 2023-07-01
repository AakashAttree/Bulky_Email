import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormArray, FormGroup, FormGroupDirective } from '@angular/forms';

@Component({
  selector: 'app-once-options',
  templateUrl: './once-options.component.html',
  styleUrls: ['./once-options.component.css']
})
export class OnceOptionsComponent implements OnInit {
  selected!: Date | null;
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

  printDate(){
    console.log(this.selected);
  }
}
