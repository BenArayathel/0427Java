import { Component, OnInit } from '@angular/core';

import { Hero } from '../hero';
import { HeroService } from '../hero.service';

/* 
	This class defines a heroes array property.
	The constructor expects an injection of HeroService into a private heroService property.
	The ngOnInit() lifecycle hook calls getHeroes().
	The getHeroes() returns a sliced list of heroes at pos 1 and 5 (so returns only top four heroes).
*/

@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

	constructor(private heroService: HeroService) { }

	ngOnInit() {
		this.getHeroes();
	}

	heroes: Hero[] = [];

	getHeroes(): void {
		this.heroService.getHeroes().subscribe(heroes => this.heroes = heroes.slice(1, 5));
	}
}