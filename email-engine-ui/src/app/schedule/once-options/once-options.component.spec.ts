import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OnceOptionsComponent } from './once-options.component';

describe('OnceOptionsComponent', () => {
  let component: OnceOptionsComponent;
  let fixture: ComponentFixture<OnceOptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OnceOptionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OnceOptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
