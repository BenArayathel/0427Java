import { Component, OnInit } from '@angular/core';
import { Planet } from './planet';

/*
	What is a directive?
		A custom HTML keyword created by Angular.
		There are multiple types of directives that exist:
			1. Components - directives with a template.
			2. Structural directives - change the DOM layout by adding and removing DOM elements.
			3. Attribute directives - change the appearance of behavior of an element, component, or another directive.

	Types of Binding:
		1. One-way binding (we've used interpolation, there are many other ways).
		2. Two-way binding using ngModel
		3. Property binding (this is a one-way binding)
		4. Event binding
*/

@Component({
	selector: 'app-planet-list',
	templateUrl: './planet-list.component.html',
	styleUrls: ['./planet-list.component.css']
})
export class PlanetListComponent {

	// used to demo interpolation from the HTML page
	currentLoggedInUser = "Mars";

	// used to demo *ngIf from the HTML page


	// dealing with images
	imageWidth = 100;
	imageMargin = 2;
	showImage = false;

	// used to demo two-way binding in the HTML page
	actualInputField = "";

	// getter
	get inputField() {
		return this.actualInputField;
	}

	// setter
	set inputField(temp: string) {
		this.actualInputField = temp;

		// True or False
		this.filteredPlanets = this.actualInputField ? this.performFilter(this.inputField) : this.planets;
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

	togleImage() {
		this.showImage = !this.showImage;
	}

	performFilter(filterValue: string): Planet[] {
		filterValue = filterValue.toLocaleLowerCase();

		return this.planets.filter(
			(planet: Planet) => planet.name.toLocaleLowerCase().indexOf(filterValue) !== -1
		);
	}
}
