import { Component, OnInit } from '@angular/core';
import { Planet } from './planet';

/**
 * What is a directive?
 *    A directive is a CUSTOM html keyword that has been created by Angular.
 *    There are multiple types of directives that exist:
 *      1) Component Directives
 *      2) Structural Directives
 *      3) Attributive Directives
 * 
 * Types of binding?
 * 1) One-way binding (we've used interpolation, there are many other ways of doing it)
 * 2) 2-way binding using ngModel 
 * 3) Property Binding (This is a one way binding)
 * 4) Event binding 
 */

@Component({
  selector: 'app-planet-list',
  templateUrl: './planet-list.component.html',
  styleUrls: ['./planet-list.component.css']
})
export class PlanetListComponent {

  //used to demo interpolation in the HTML page
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

  constructor() {
    this.planets = [
      {
        name: 'Mercury',
        image: 'https://cdn.mos.cms.futurecdn.net/GA4grWEsUYUqH58cDbRBw8-1200-80.jpg'
      },
      {
        name: 'Venus',
        image: 'https://www.jpl.nasa.gov/spaceimages/images/largesize/PIA00270_hires.jpg'
      },
      {
        name: 'Neptune',
        image: 'https://solarsystem.nasa.gov/system/stellar_items/image_files/90_feature_1600x900_4.jpg'
      }
    ];

    this.filteredPlanets = this.planets;
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
