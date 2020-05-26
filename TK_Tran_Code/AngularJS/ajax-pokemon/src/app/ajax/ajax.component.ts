import { Component, OnInit } from '@angular/core';
import { PokemonService } from '../services/pokemon.service';

@Component({
	selector: 'app-ajax',
	templateUrl: './ajax.component.html',
	styleUrls: ['./ajax.component.css']
})
export class AjaxComponent implements OnInit {

	constructor(private pokeService: PokemonService) {

	}

	ngOnInit(): void {
	}

	pokeID: number;
	pokeName = "";
	pokeImageUrl = "";

	// Messy method
	pokeButtonClickedMessy() {
		console.log("Messy button clicked!");
		console.log(this.pokeID);

		this.pokeService.setUrl(this.pokeID);

		this.pokeService.capturePokemonMessy().subscribe(
			(data) => {
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

	// Clean method
	pokeButtonClickedClean() {
		console.log("Clean button clicked!");
		console.log(this.pokeID);

		this.pokeService.setUrl(this.pokeID);

		this.pokeService.capturePokemonClean().subscribe(
			(data) => {
				const field = "front_default";

				// This is much cleaner!
				this.pokeName = data.name;
				this.pokeImageUrl = data.sprites[field];
			}
		)
	}
}