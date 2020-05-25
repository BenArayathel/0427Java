import { Injectable } from '@angular/core';
import { Hero } from './hero';
import { HEROES } from './mock-heroes';
import { Observable, of } from 'rxjs';
import { MessageService } from './messages.service';

// this service is sort of like our connection to a db
// we are still using a connection to mock data for the project, but
// it could be to a sql oracle server instead. the components
// don't know the difference
@Injectable({
  providedIn: 'root'
})
export class HeroService {

  // this returns a signle array of all the mock values,
  // which is also imported above, of Object type Hero, 
  // from the interface from earlier
  getHeroes(): Observable<Hero[]> {
    this.messageService.add('HeroService: fetched heroes');
    return of(HEROES);
  }

  constructor(private messageService: MessageService) { }
}
