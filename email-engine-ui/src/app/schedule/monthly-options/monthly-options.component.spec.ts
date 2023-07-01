import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthlyOptionsComponent } from './monthly-options.component';

describe('MonthlyOptionsComponent', () => {
  let component: MonthlyOptionsComponent;
  let fixture: ComponentFixture<MonthlyOptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MonthlyOptionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MonthlyOptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
