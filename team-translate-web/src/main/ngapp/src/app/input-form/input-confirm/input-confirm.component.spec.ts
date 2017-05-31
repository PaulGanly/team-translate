import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InputConfirmComponent } from './input-confirm.component';

describe('InputConfirmComponent', () => {
  let component: InputConfirmComponent;
  let fixture: ComponentFixture<InputConfirmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InputConfirmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InputConfirmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
