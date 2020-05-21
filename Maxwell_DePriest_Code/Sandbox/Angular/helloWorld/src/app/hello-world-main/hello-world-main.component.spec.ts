import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HelloWorldMainComponent } from './hello-world-main.component';

describe('HelloWorldMainComponent', () => {
  let component: HelloWorldMainComponent;
  let fixture: ComponentFixture<HelloWorldMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HelloWorldMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HelloWorldMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
