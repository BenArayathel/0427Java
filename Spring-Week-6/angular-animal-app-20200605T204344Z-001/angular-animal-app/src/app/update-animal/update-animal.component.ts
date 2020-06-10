import { Component, OnInit } from '@angular/core';
import { Animal } from '../animal';
import { AnimalService } from '../animal.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-animal',
  templateUrl: './update-animal.component.html',
  styleUrls: ['./update-animal.component.css']
})
export class UpdateAnimalComponent implements OnInit {
  private animal:Animal;

  constructor(private service:AnimalService,private router:Router) {
    this.animal=new Animal();
   }


   
   updateAnimal(){
    this.service.updateAnimal(this.animal).subscribe(res=>this.router.navigate(['/listAnimals']));
    this.animal=new Animal();
  }

  ngOnInit() {
  }

}
