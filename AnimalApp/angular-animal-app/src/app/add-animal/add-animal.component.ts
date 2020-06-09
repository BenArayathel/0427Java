import { Component, OnInit } from '@angular/core';
import { AnimalService } from '../animal.service';
import { Animal } from '../animal';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-animal',
  templateUrl: './add-animal.component.html',
  styleUrls: ['./add-animal.component.css']
})
export class AddAnimalComponent implements OnInit {

  private _animal:Animal;

  constructor(private service:AnimalService, private router:Router) {
    this._animal = new Animal();
  }

  createAnimal() {
    this.service.createAnimal(this._animal).subscribe(
      res => this.router.navigate(["/listAnimals"])
    );
    this._animal = new Animal();
  }

  ngOnInit(): void {
  }

  get animal():Animal {
    return this._animal;
  }

  set animal(animal:Animal) {
    this._animal = animal;
  }

}
