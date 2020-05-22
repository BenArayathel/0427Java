import { Component, OnInit } from '@angular/core';
import { Planet } from '../shared/planet';
import { PlanetService } from '../shared/planet.service';


@Component({
  selector: 'app-planet-list',
  templateUrl: './planet-list.component.html',
  styleUrls: ['./planet-list.component.css']
})
export class PlanetListComponent {

  //used to demo interpolation in the HTML page
  currentLoggedInUser = "Mars";

  //used to demo *ngIf from the HTML page

  //dealing with images
  imageWidth=100;
  imageMargin=2;
  showImage=false;

  //used to demo 2-way binding in the HTML page
  actualInputField = '';

  get inputField() {
    return this.actualInputField;
  }

  set inputField(temp: string) {
    this.actualInputField = temp;

    this.filteredPlanets=this.actualInputField?
          this.performFilter(this.inputField):this.planets;
          //      true                           false
  }

  planets: Planet[];
  filteredPlanets: Planet[];

  constructor(private myPlanetService: PlanetService) {
    this.planets = myPlanetService.getPlanet();

    this.filteredPlanets = this.planets;
   }

   toggleImage() {
     this.showImage = !this.showImage;
   }

   performFilter(filterValue: string): Planet[] {
     filterValue=filterValue.toLocaleLowerCase();

     return this.planets.filter(
       (planet: Planet) =>
       planet.name.toLocaleLowerCase().indexOf(filterValue) !== -1
     );
   }

}
