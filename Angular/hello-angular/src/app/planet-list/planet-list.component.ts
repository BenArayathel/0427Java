import { Component, OnInit } from '@angular/core';
import { Planet } from './planet'

/**
 * What is a directive?
 *    A directive is a CUSTOM html keyword that has been created by Angular.
 *    There are multiple types of directives that exist:
 *      - Component Directives
 *      - Structural Directives
 *      - Attribute Directives
 * 
 * Types of binding
 *  - One-way Binding (we've used interpolation, there are many other ways of doing it)
 *  - Property Binding (This is a one-way binding)
 *  - Two-way Binding using ngModel
 *  - Event Binding
 */


@Component({
  selector: 'app-planet-list',
  templateUrl: './planet-list.component.html',
  styleUrls: ['./planet-list.component.css']
})
export class PlanetListComponent {


  // used to demo interpolation in the HTML page
  currentLoggedInUser = "Mars";

  // used to demo structural tags (e.g. *ngIf from the HTML page)

  // dealilng with images
  imageWidth=100;            
  imageMargin=2;
  showImage=false;

  // used to demo 2-way binding in the HTML page
  acutalInputField = '';

  get inputField() {
    return this.acutalInputField;
  }

  set inputField(temp:string) {
    this.acutalInputField = temp;

    this.filteredPlanets = this.acutalInputField?
        this.performFilter(this.inputField) : this.planets;
        //        true                      :     false
  }

  planets:Planet[];
  filteredPlanets:Planet[];

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

  toggleImage() {
    // toggles showing the image
    this.showImage = !this.showImage;
  }

  performFilter(filterValue:string):Planet[] {
    filterValue = filterValue.toLocaleLowerCase();

    return this.planets.filter(
      (planet:Planet) => 
      planet.name.toLocaleLowerCase().indexOf(filterValue)!==-1
    );
  }

}
