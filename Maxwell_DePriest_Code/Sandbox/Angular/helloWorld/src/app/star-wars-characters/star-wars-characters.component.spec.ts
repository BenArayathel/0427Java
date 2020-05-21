import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StarWarsCharactersComponent } from './star-wars-characters.component';

describe('StarWarsCharactersComponent', () => {
  let component: StarWarsCharactersComponent;
  let fixture: ComponentFixture<StarWarsCharactersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StarWarsCharactersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StarWarsCharactersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
