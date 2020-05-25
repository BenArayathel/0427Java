import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs'; // Observable is a class from the RxJS library
import { MessageService } from './message.service';

import { Hero } from './hero';
import { HEROES } from './mock-heroes';

// A class marked with @Injectable decorator means it participates in the "dependency injection system".
// @Injectable() decorator accepts a metadata object for this service..the same way @Component() decorator did for component classes.
@Injectable({
	// We must make HeroService available to the dependency injection system before Ang can "inject" it into HeroesComponent.
	// Do this by registering a "provider"; something that can create or deliver a service.
	// In our case, it instantiates the HeroService class to provide the service.
	providedIn: 'root' // by default, the CLI registers a provider with the "root injector" for our service

	// When provided the service at root level, Ang creates a single, shared instance of HeroService and INJECTS it into ANY class that asks for it.
})

// Services handle all data-access logic so that components can focus on presentation.
export class HeroService {

	// Inject the MessageService into HeroService; a typical "service-in-service" scenario
	constructor(private messageService: MessageService) { }

	// DATA ACCESS LOGIC INSIDE SERVICE

	// Declaring getHeroes() for any class to use
	getHeroes(): Observable<Hero[]> {
		// Using MessageService to send a message when heroes are fetched
		this.messageService.add('HeroService - Fetched Heroes: ');

		// of(HEROES) returns an Observable<Hero[]> that emits a SINGLE VALUE, the array of mock heroes
		return of(HEROES);
	}

}

// The HeroService is not ready to plug into HeroesComponent!