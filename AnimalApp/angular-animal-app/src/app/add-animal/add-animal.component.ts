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

  private animal:Animal;

  constructor(private service:AnimalService, private router:Router) {
    this.animal = new Animal();
  }

  createAnimal() {
    this.service.createAnimal(this.animal).subscribe(
      res => this.router.navigate(["/listAnimals"])
    );
    this.animal = new Animal();
  }

  ngOnInit(): void {
  }

}
