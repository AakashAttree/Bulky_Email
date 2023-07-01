import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowEmailsComponent } from './show-emails.component';

describe('ShowEmailsComponent', () => {
  let component: ShowEmailsComponent;
  let fixture: ComponentFixture<ShowEmailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowEmailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowEmailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
