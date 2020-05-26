import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Hero } from './hero';

@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const heroes = [
      { id: 11, name: 'Ben Grimm' },
      { id: 12, name: 'Sue Storm' },
      { id: 13, name: 'Dr. Xavier' },
      { id: 14, name: 'Cyclops' },
      { id: 15, name: 'Jubilee' },
      { id: 16, name: 'Reed Richards' },
      { id: 17, name: 'Iron Man' },
      { id: 18, name: 'Vision' },
      { id: 19, name: 'Captain America' },
      { id: 20, name: 'Ant Man' }
    ];
    return {heroes};
  }

  // Overrides the genId method to ensure that a hero always has an id.
  // If the heroes array is empty,
  // the method below returns the initial number (11).
  // if the heroes array is not empty, the method below returns the highest
  // hero id + 1.
  genId(heroes: Hero[]): number {
    return heroes.length > 0 ? Math.max(...heroes.map(hero => hero.id)) + 1 : 11;
  }
}
