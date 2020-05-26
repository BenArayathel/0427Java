import { Component, OnInit } from '@angular/core';
import { Hero } from '../hero';
// import { HEROES } from '../mock-heroes'; // don't need this now
// that we have an injector and provider service
import { HeroService } from '../hero.service';
import { MessageService } from '../messages.service';

@Component({
  // the selector is how you can reference it in CSS
  selector: 'app-heroes',
  // name of HTML file for this component
  templateUrl: './heroes.component.html',
  // name of CSS for this component
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {
  
  // no longer how we are selecting them
  // selectedHero: Hero; // this typescript syntax means <variable> ofType <object>
  
  // first hardcoded hero
  // hero: Hero = {
  //   id: 1,
  //   name: 'First PersonShooter'
  // };

  // progressed on to mock-heroes, also hardcoded
  // heroes = HEROES;
  
  // third step, now it is equal to our service instead of
  // directly to mock data
  heroes: Hero[];

  // part of the onclick logic
  // click on an item, it assigns it to be
  // the (class? variable?) of selectedHero of type Hero

  // onSelect(hero: Hero): void {
  //   this.selectedHero = hero;
  //   this.messageService.add(`HeroService: Selected hero id = ${hero.id}`);
  // }

  // a method to get the heroes from the service layer
  // method of the same name, which gets the 'db' data
  // getHeroes(): void {
  //   this.heroes = this.heroService.getHeroes();
  // }

  // this is injecting a SINGLETON instance, btw
  constructor(private heroService: HeroService) { }

  ngOnInit(): void {
    this.getHeroes();
  }
  
  // new way using observables, closer to actual HTTP request approach
  getHeroes(): void {
    this.heroService.getHeroes()
      .subscribe(heroes => this.heroes = heroes);
  }

  // why does add go here, in the component for heroes,
  // but the addHero goes in as a service? what is the thought behind
  // that organization?

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.heroService.addHero({ name } as Hero)
      .subscribe(hero => {
        this.heroes.push(hero);
      });
  }

  delete(hero: Hero): void {
    this.heroes = this.heroes.filter(h => h !== hero);
    this.heroService.deleteHero(hero).subscribe();
  }

  
  
  // ngOnInit(): {
  //   this.getHeroes();
  // }

}
