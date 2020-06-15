import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetByIdAnimalComponent } from './get-by-id-animal.component';

describe('GetByIdAnimalComponent', () => {
  let component: GetByIdAnimalComponent;
  let fixture: ComponentFixture<GetByIdAnimalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetByIdAnimalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetByIdAnimalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
