import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyownComponent } from './myown.component';

describe('MyownComponent', () => {
  let component: MyownComponent;
  let fixture: ComponentFixture<MyownComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyownComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
