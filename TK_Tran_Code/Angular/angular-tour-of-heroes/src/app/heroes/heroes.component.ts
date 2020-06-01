import { Component, OnInit } from '@angular/core'; // always import Component symbol from core lib and annotate the component class w/ @Component

import { Hero } from '../hero'; // imports the Hero interface from hero.ts
import { HeroService } from '../hero.service'; // imports the singleton HeroService to handle data access logic

@Component({
	// The Angular CLI generated three metadata properties:
	selector: 'app-heroes', // this component's element selector; this is what's placed inside the shell template (app.component.html) as an element to display HeroesComponent
	templateUrl: './heroes.component.html', // the location of this component's template (HTML) file
	styleUrls: ['./heroes.component.css'] // the location of this component's private CSS file
})
export class HeroesComponent implements OnInit {

	// INJECTIOn
	constructor(private heroService: HeroService) { }

	/* 
		While you COULD call getHeroes() inside the constructor, it's not best practice.
		Reserve the constructor for simple initialization such as linking constructor params to properties.
		Constructors shouldn't DO anything.
		Instead, call getHeroes() inside ngOnInit() lifecycle hook and let Ang call ngOnInit() at an appropriate time AFTER constructing a HeroesComponent instance.
		ngOnInit() (one of many "lifecycle hooks") called shortly after component creation; it's a good place to put initialization logic!
	*/
	ngOnInit(): void {
		this.getHeroes();
	}

	heroes: Hero[];

	// IMPROVED, ASYNCHRONOUS VERSION
	getHeroes(): void {
		this.heroService.getHeroes().subscribe(heroes => this.heroes = heroes);
		// .subscribe() is CRITICAL for ensuring ASYNCHRONOUS; it passes the emitted array to the callback (which sets the component's heroes property).
		// This asynchronous approach works if HeroService requests heroes from a server, for example.
	}

	// BAD, SYNCHRONOUS VERSION
	// getHeroes(): void {
	// 	this.heroes = this.heroService.getHeroes();
	// }
}

/*
	HeroService.getHeroes() has a SYNCHRONOUS SIGNATURE, implying HeroService can fetch heroes SYNCHRONOUSLY.
	HeroesComponent consumes the result of getHeroes() as if heroes could be fetched synchronously.
	This won't work in a real application!!! We're getting away with it bc the service is returning MOCK HEROES.
	If our app fetches from a remote server, that's an ASYNCHRONOUS operation.
		HeroService must wait for server to respond, getHeroes() cannot return hero data immediately, and browser will not block while the service waits!
	HeroService.getHeroes() must have an ASYNCHRONOUS SIGNATURE of some kind.
*/