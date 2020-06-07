import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { AnimalService } from "../animal.service";
import { Animal } from "../animal";

@Component({
  selector: 'app-animal-edit',
  templateUrl: './animal-edit.component.html',
  styleUrls: ['./animal-edit.component.css']
})
export class AnimalEditComponent implements OnInit {

  private animal: Animal = {
    id: -1,
    name: "pick from table to edit",
    age: 0,
    avatar: "pick to edit"
  };
  
  private animalId: number;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private animalService: AnimalService
  ) { }

  ngOnInit() {
    //this.animal.name = "h"; // setting to 'h' so it is not NULL !

    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      if (paramMap.has('id')) {
        this.animalId = +paramMap.get('id'); // parse string to a number
        this.animalService.getAnimalById(this.animalId).subscribe(data => {
          this.animal = {id: data.id, name: data.name, age: data.age, avatar: data.avatar };
          console.log("here returned is the name field: " + this.animal.name);
        });
      } else {
        //this.mode = 'create';   // not sure what if anything here yet
        //this.postId = null;
      }
    });
  }

  editAnimal(localanimal: Animal){
    this.animalService.updateAnimal(localanimal).subscribe(data=>{
      console.log(data);
      this.router.navigate(['/listAnimals']);
    });
  }

}
