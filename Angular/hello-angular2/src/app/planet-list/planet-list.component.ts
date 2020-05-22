import { Component } from '@angular/core';
import { Planet } from '../shared/planet';
import { PlanetService } from '../shared/planet.service';

import { ActivatedRoute } from '@angular/router';

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

  // Demoing URL parameters
  currentUser = '';

  constructor(private myPlanetService:PlanetService, private route:ActivatedRoute) {
    /**
     * There is an entity inside of Angular called the injector...
     * This injector entity will create objects of all of the services you provide it and
     *  WHENEVER it sees a constructor with a parameter of the SAME type as an object the
     *  injector contains. Then the injector will INJECT that object into the constructor
     *  (The myPlanetService parameter got injected with stuff from PlanetService)
     */
    this.planets = myPlanetService.getPlanet();
    this.filteredPlanets = this.planets;
  }

  ngOnInit():void {
    this.currentUser = this.route.snapshot.paramMap.get('myVariable');
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
