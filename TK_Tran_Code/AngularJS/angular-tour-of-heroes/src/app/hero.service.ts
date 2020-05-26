import { Injectable } from '@angular/core'; // provides @Injectable declarator which allows this class to be injectable
import { Observable, of } from 'rxjs'; // Observable is a class from the RxJS library
import { catchError, map, tap } from 'rxjs/operators'; // provides mechanisms for "catching" errors from HTTP requests
import { HttpClient, HttpHeaders } from '@angular/common/http'; // enable HTTP requests

import { Hero } from './hero';
import { HEROES } from './mock-heroes';
import { MessageService } from './message.service';


@Injectable({
	// Makes HeroService available to the DI system by registering a "provider"; something that can create or deliver a service
	// In our case, it instantiates this HeroService class which will provide a service
	providedIn: 'root' // by default, "root"" makes this service app-wide
})
export class HeroService {

	// Injects other services into HeroService; a typical "service-in-service" scenario
	constructor(
		private http: HttpClient,
		private messageService: MessageService
	) { }

	// Defines the URL to our web API
	private heroesUrl = 'api/heroes';

	// Since MessageService is called so frequently, we wrap it in a private log() method
	private log(message: string) {
		this.messageService.add(`HeroService: ${message}`);
	}

	// Returns an array of all heroes
	getHeroes(): Observable<Hero[]> {
		// GET heroes from the server
		return this.http.get<Hero[]>(this.heroesUrl) // to catch potential errors, we "pipe" the Observable result from http.get() through an RxJS catchError() operator
			.pipe( // the catchError() operator INTERCEPTS an Observable that FAILED
				catchError(this.handleError<Hero[]>("getHeroes", []))); // catchError() passes the error to an error handler
	}

	// Returns a single hero by their ID number
	getHero(id: number): Observable<Hero> {
		this.messageService.add(`HeroService: fetched hero id=${id}`); // note the use of backticks (`) that define a JS TEMPLATE LITERAL for embedding things

		// of(HEROES.find()) queries through HEROES array and returns a hero with a matching id
		return of(HEROES.find(hero => hero.id === id));

		// Note: both getHeroes() and getHero() have asynchronous signatures; they returna a mock heroes as an Observable, using the RxJS of() function
	}

	/**
	 * Handle Http operation that failed.
	 * Let the app continue.
	 * @param operation - name of the operation that failed
	 * @param result - optional value to return as the observable result
	 */
	private handleError<T>(operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {

			// TODO: send the error to remote logging infrastructure
			console.error(error); // log to console instead

			// TODO: better job of transforming error for user consumption
			this.log(`${operation} failed: ${error.message}`);

			// Let the app keep running by returning an empty result.
			return of(result as T);
		};
	}
}

// HeroService now ready to inject into HeroesComponent!