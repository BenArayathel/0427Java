import { Component, OnInit } from '@angular/core';
import { Animal } from '../animal';
import { AnimalService } from '../animal.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-delete-animal',
  templateUrl: './delete-animal.component.html',
  styleUrls: ['./delete-animal.component.css']
})
export class DeleteAnimalComponent implements OnInit {
  private animal:Animal;

  // constructor() { }

  constructor(private service:AnimalService,private router:Router) {
    this.animal=new Animal();
   }

   deleteAnimalById( id){
    this.service.deleteAnimalById(id).subscribe(res=>this.router.navigate(['/listAnimals']));

   }

  ngOnInit() {
  }

}
