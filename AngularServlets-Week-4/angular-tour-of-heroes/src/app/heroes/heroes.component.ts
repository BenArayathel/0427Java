import { Component, OnInit } from '@angular/core';
import { Hero } from "../hero";
//import { HEROES } from "../mock-heroes";
import { HeroService } from '../hero.service';
import { MessageService } from "../message.service";

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {

  hero: Hero = {
    id: 1,
    name: 'Windstorm'
  };

  //heroes = HEROES;
  heroes: Hero[];

  selectedHero: Hero;
  // onSelect(hero: Hero): void {
  // this.selectedHero = hero;
  // }

  onSelect(hero: Hero): void {
    this.selectedHero = hero;
    this.messageService.add(`HeroService: Selected hero id=${hero.id}`);
  }

  constructor(
    private heroService: HeroService,
    private messageService: MessageService) { }

  ngOnInit() {
    this.getHeroes();
  }

 

  //hero = 'Windstorm';

  // ORIGINAL VERSION
  // getHeroes(): void {
  //   this.heroes = this.heroService.getHeroes();
  // }

  // OBSERVABLE VERSION
  getHeroes(): void {
    this.heroService.getHeroes()
        .subscribe(heroes => this.heroes = heroes);
  }

}
