import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormGroupDirective } from '@angular/forms';

@Component({
  selector: 'app-time',
  templateUrl: './time.component.html',
  styleUrls: ['./time.component.css']
})
export class TimeComponent implements OnInit {
  form!: FormGroup
  @Input('formGroup') formGroup!:FormGroup
  constructor(private rootForm:FormGroupDirective) { 
    this.form = this.rootForm.control
  }

  ngOnInit(): void {
  }

}
