import { Component, OnInit, Input } from '@angular/core'; // @angular/core amended to include the Input symbol
import { Hero } from '../hero';

// We're delegating this component as the child component of the parent HeroesComponent.
// The parent HeroesComponent controls the child HeroDetailComponent by sending it a new hero to display whenever a user selects a hero from the list.
// This component simply retrieves a hero object through its hero property and displays it.
@Component({
	selector: 'app-hero-detail',
	templateUrl: './hero-detail.component.html',
	styleUrls: ['./hero-detail.component.css']
})

export class HeroDetailComponent implements OnInit {

	constructor() { }

	ngOnInit(): void {
	}

	// hero property MUST BE AN INPUT PROPERTY, annotation with @Input() bc..
	// ..the EXTERNAL HeroesComponent will bind to it like this: <app-hero-detail [hero]="selectedHero"></app-hero-detail>
	@Input() hero: Hero;
}
