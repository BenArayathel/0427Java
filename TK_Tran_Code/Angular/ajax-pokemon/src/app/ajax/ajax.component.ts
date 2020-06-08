import { Component, OnInit } from '@angular/core';
import { PokemonService } from '../services/pokemon.service';

@Component({
	selector: 'app-ajax',
	templateUrl: './ajax.component.html',
	styleUrls: ['./ajax.component.css']
})
export class AjaxComponent implements OnInit {

	// On creation of AjaxComponent, injects a service of type PokemonService and names it pokeService
	constructor(private pokeService: PokemonService) {
	}

	ngOnInit(): void {
	}

	pokeTitle: string = "Pokemon API";
	pokeID: number;
	pokeName = "";
	pokeImageUrl = "";

	// Using PokemonService's messy AJAX method
	catchPokemonMessy() {
		console.log("Messy button clicked!");
		console.log("User entered: " + this.pokeID);

		this.pokeService.setUrl(this.pokeID);

		this.pokeService.fetchPokemonMessy().subscribe((data) => {
			const ourField1 = 'name';
			const ourField2 = 'sprites';
			const ourField3 = 'front_default';

			let spritesArray: object;

			// This can get messy!
			this.pokeName = data[ourField1];
			spritesArray = data[ourField2];
			this.pokeImageUrl = spritesArray[ourField3];
		}
		)
	}

	// Using PokemonService's clean AJAX method
	catchPokemonClean() {
		console.log("Clean button clicked!");
		console.log("User entered: " + this.pokeID);

		this.pokeService.setUrl(this.pokeID);

		this.pokeService.fetchPokemonClean().subscribe((data) => {
			const field = "front_default";

			// This is much cleaner!
			this.pokeName = data.name;
			this.pokeImageUrl = data.sprites[field];
		}
		)
	}
}