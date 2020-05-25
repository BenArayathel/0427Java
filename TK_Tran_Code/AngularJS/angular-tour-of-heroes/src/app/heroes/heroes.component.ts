import { Component, OnInit } from '@angular/core'; // always import Component symbol from core lib and annotate the component class w/ @Component

import { Hero } from '../hero'; // imports the Hero interface from hero.ts
import { HeroService } from '../hero.service'; // imports HeroService to handle data access logic
import { MessageService } from '../message.service'; // imports MessageService to handle the displaying of messages

// @Component() is a decorator func that specifies it's metadata.
// Components are classes that associated itself with a companion "template" (fancy HTML w/ Ang data-binding syntax).
@Component({
	// The Angular CLI generated three metadata properties:
	selector: 'app-heroes', // this component's element selector; this is what's placed inside the shell AllComponent as an element to display HeroesComponent
	templateUrl: './heroes.component.html', // the location of this component's template (html) file
	styleUrls: ['./heroes.component.css'] // the location of this component's private CSS file
})

// Always export component classes so we can import it elsewhere..like in the root AppModule (app package).
export class HeroesComponent implements OnInit {

	// INJECTING THE SERVICES:
	// @Param defines a private heroService property AND identifies it as a HeroService injection site
	// Also inject a separate, singleton MessageService 
	constructor(private heroService: HeroService, private messageService: MessageService) { }

	/* 
		While you COULD call getHeroes() inside the constructor, it's not best practice.
		Reserve the constructor for simple initialization such as linking constructor params to properties.
		Constructors shouldn't DO anything.
		Instead, call getHeroes() inside ngOnInit() lifecycle hook and let Ang call ngOnInit() at an appropriate time AFTER constructing a HeroesComponent instance.
	*/

	// ngOnInit() (one of many "lifecycle hooks") called shortly after component creation; it's a good place to put initialization logic!
	ngOnInit(): void {
		this.getHeroes();
	}

	heroes: Hero[];
	selectedHero: Hero;

	onSelect(hero: Hero): void {
		this.selectedHero = hero; // assigns the clicked hero from the html to this component's selectedHero

		// Everytime a hero is slected, add onto the message cache
		this.messageService.add(`HeroService - Selected Hero ID=${hero.id}`);
	}

	// BAD, SYNCHRONOUS VERSION
	// getHeroes(): void {
	// 	this.heroes = this.heroService.getHeroes();
	// }

	// IMPROVED, ASYNCHRONOUS VERSION
	getHeroes(): void {
		this.heroService.getHeroes()
			.subscribe(heroes => this.heroes = heroes);
		// .subscribe() is CRITICAL; it passes the emitted array to the callback, which sets the component's heroes property
		// This asynchronous approach WILL WORK if HeroService requests heroes from a server
	}
}

/*
	HeroService.getHeroes() has a SYNCHRONOUS SIGNATURE, implying HeroService can fetch heroes SYNCHRONOUSLY.
	HeroesComponent consumes the result of getHeroes() as if heroes could be fetched synchronously.
	This won't work in a real application!!! We're getting away with it bc the service is returning MOCK HEROES.
	If our app fetches from a remote server, that's an ASYNCHRONOUS operation.
		HeroService must wait for server to respond, getHeroes() cannot return hero data immediately, and browser will not block while the service waits!
	HeroService.getHeroes() must have an ASYNCHRONOUS SIGNATURE of some kind.
*/