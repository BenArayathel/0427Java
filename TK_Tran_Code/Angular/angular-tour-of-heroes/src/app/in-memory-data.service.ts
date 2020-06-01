import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Hero } from './hero';

@Injectable({
	providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
	createDb() {
		const HEROES = [
			{ id: 11, name: 'Superman' },
			{ id: 12, name: 'Batman' },
			{ id: 13, name: 'Spiderman' },
			{ id: 14, name: 'Thor' },
			{ id: 15, name: 'Iron Man' },
			{ id: 16, name: 'Black Widow' },
			{ id: 17, name: 'Hawkeye' },
			{ id: 18, name: 'Dr. Strange' },
			{ id: 19, name: 'Hulk' },
			{ id: 20, name: 'Captain America' }
		];
		return { HEROES };
	}

	// Overrides the genId method to ensure that a hero always has an id.
	// If the heroes array is empty, the method below returns the initial number (11).
	// If the heroes array is not empty, the method below returns the highest hero id + 1.
	genId(heroes: Hero[]): number {
		return heroes.length > 0 ? Math.max(...heroes.map(hero => hero.id)) + 1 : 11;
	}
}