import { Component, OnInit } from '@angular/core';
import { AnimalService } from '../animal.service';
import { Animal } from '../animal';

@Component({
  selector: 'app-animal-list',
  templateUrl: './animal-list.component.html',
  styleUrls: ['./animal-list.component.css']
})
export class AnimalListComponent implements OnInit {

  private _animals:Animal[];

  constructor(private service:AnimalService) { }

  ngOnInit(): void {
    this.service.getAllAnimals().subscribe(
      data => this._animals = data
    );
  }

  get animals():Animal[] {
    return this._animals;
  }

  set animals(animalArray:Animal[]) {
    this._animals = animalArray;
  }

}
