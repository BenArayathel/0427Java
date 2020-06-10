import { Component, OnInit } from '@angular/core';
import { Animal } from '../animal';
import { AnimalService } from '../animal.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-get-by-id-animal',
  templateUrl: './get-by-id-animal.component.html',
  styleUrls: ['./get-by-id-animal.component.css']
})
export class GetByIdAnimalComponent implements OnInit {

  private animal:Animal;

  // constructor() { }

  constructor(private service:AnimalService,private router:Router) {
    this.animal=new Animal();
   }

   getAnimalById( id){
    this.service.getAnimalById(id).subscribe(res=>this.animal=res);

   }

  //  ngOnInit() {
  //   this.service.getAllAnimals().subscribe(data=>{
  //     this.animals=data;
  //   });


  ngOnInit() {
  }

}
