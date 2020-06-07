import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimalEditComponent } from './animal-edit.component';

describe('AnimalEditComponent', () => {
  let component: AnimalEditComponent;
  let fixture: ComponentFixture<AnimalEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnimalEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnimalEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
