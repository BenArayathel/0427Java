import { Component, OnInit } from '@angular/core';
import { AnimalService } from '../animal.service';
import { Animal } from '../animal';
import { Router } from "@angular/router";

@Component({
  selector: 'app-animal-list',
  templateUrl: './animal-list.component.html',
  styleUrls: ['./animal-list.component.css']
})
export class AnimalListComponent implements OnInit {
private animals:Animal[];
  constructor(
    private service:AnimalService,
    private router : Router) {
   }

  ngOnInit() {
    this.service.getAllAnimals().subscribe(data=>{
      this.animals=data;
    });
  }

  delete( id: number ){
    console.log("this is the id: " + id);
    this.service.deleteAnimal(id).subscribe(data=>{
      console.log(data);
      this.ngOnInit();
      // this.router.navigate(['/listAnimals']) // this did not work
    });
  }

  edit( id: number ){
    console.log("this is the id: " + id);
    this.router.navigate([`/editAnimal/${id}`]);
  }

}
