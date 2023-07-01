import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DailyOptionsComponent } from './daily-options.component';

describe('DailyOptionsComponent', () => {
  let component: DailyOptionsComponent;
  let fixture: ComponentFixture<DailyOptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DailyOptionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DailyOptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
