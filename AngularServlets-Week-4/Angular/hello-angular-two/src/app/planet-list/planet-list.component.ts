import { Component, OnInit } from '@angular/core';
import { Planet } from '../shared/planet';
import { PlanetService } from '../shared/planet.service';

import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-planet-list',
  templateUrl: './planet-list.component.html',
  styleUrls: ['./planet-list.component.css']
})
export class PlanetListComponent {

  //used to deom interpolation in the HTML page
  currentLoggedInUser = "Mars";

  //used to demo *ngIf from the HTML oage

  //dealing with images
  imageWidth = 100;
  imageMargin = 2;
  showImage = false;

  //used to demo 2-way binding in the HTML page
  actualInputfield = '';

  get inputField() {
    return this.actualInputfield;
  }
  set inputField(temp: string){
    this.actualInputfield = temp;

    this.filteredPlanets = this.actualInputfield?
            this.performFilter(this.inputField) : this.planets;
            //        true                            false
  }

  planets: Planet[];
  filteredPlanets: Planet[];

  //Demoing URL paramaeters
  currentUser = '';

    //REMINDER
    //TS will automatically create a field when the parameter has an
    //access modifier (and automatically assign it the argument passed through)

  constructor(private myPlanetService: PlanetService, private route: ActivatedRoute) {

    /**
     * There is an entitty inside of ANgular called the injector... 
     * this injector entity will create objects of all of the services your provide
     * it... and WHENEVER it sees  a constructor with a parameter of the SAME types
     * as an object the injector contains. then the injector will INJECT that object
     * into the constructor.
     */

    this.planets = myPlanetService.getPlanet();
    this.filteredPlanets = this.planets;

   }

 
  ngOnInit(): void {
    this.currentUser = this.route.snapshot.paramMap.get('myVariable');
  }

  toggleImage(){
    this.showImage = !this.showImage;
  }

  performFilter(filterValue: string): Planet[] {
    filterValue = filterValue.toLocaleLowerCase();

    return this.planets.filter(
      (planet: Planet) =>
      planet.name.toLocaleLowerCase().indexOf(filterValue)!== -1
    );
  }

}
