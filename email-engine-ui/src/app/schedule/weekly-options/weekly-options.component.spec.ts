import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeeklyOptionsComponent } from './weekly-options.component';

describe('WeeklyOptionsComponent', () => {
  let component: WeeklyOptionsComponent;
  let fixture: ComponentFixture<WeeklyOptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeeklyOptionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WeeklyOptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
