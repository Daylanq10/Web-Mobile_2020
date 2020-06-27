import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MashUpComponent } from './MashUp.component';

describe('NewsComponent', () => {
  let component: MashUpComponent;
  let fixture: ComponentFixture<MashUpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MashUpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MashUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
