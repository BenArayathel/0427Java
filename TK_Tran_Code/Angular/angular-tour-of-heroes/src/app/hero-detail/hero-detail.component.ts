import { Component, OnInit, Input } from '@angular/core'; // @angular/core amended to include the Input symbol.
import { ActivatedRoute } from '@angular/router'; // holds info about the route to an instance.
import { Location } from '@angular/common'; // an Angular service for interatcing with the browser.

import { Hero } from '../hero';
import { HeroService } from '../hero.service'; // gets hero data from remote server and this component will use it to get the hero-to-display.

// We're delegating this component as the child component of its parent, HeroesComponent.
// The parent HeroesComponent controls the child HeroDetailComponent by sending it a new hero to display whenever a user selects a hero from the list.
// This component simply retrieves a hero object through its hero property and displays it.
@Component({
	selector: 'app-hero-detail',
	templateUrl: './hero-detail.component.html',
	styleUrls: ['./hero-detail.component.css']
})
export class HeroDetailComponent implements OnInit {

	// INJECTION
	constructor(
		private route: ActivatedRoute,
		private heroService: HeroService,
		private location: Location
	) { }

	ngOnInit(): void {
		this.getHero();
	}

	hero: Hero;

	getHero(): void {
		const id = +this.route.snapshot.paramMap.get("id");
		this.heroService.getHero(id).subscribe(hero => this.hero = hero);

		/* 
			route.snapshot - a static image of the route info shortly after th component was created.
			paramMap - a dictionary of route param values extracted from the URL; the "id" key returns the id of the hero to fetch.
			Route params are ALWAYS STRINGS; the JS (+) operator converts the string to a number (which is what a hero id should be).
		*/
	}

	goBack(): void {
		this.location.back;
	}
}