import { Component, OnInit } from '@angular/core';
import { PokemonService } from '../services/pokemon.service';

@Component({
  selector: 'app-ajax',
  templateUrl: './ajax.component.html',
  styleUrls: ['./ajax.component.css']
})
export class AjaxComponent implements OnInit {

  pokeId:number;
  pokeName = '';
  pokeImageUrl = '';

  constructor(private pokeService:PokemonService) { }

  ngOnInit(): void {
  }

  pokeButtonClicked() {
    console.log("button is clicked!");

    console.log(this.pokeId);
    this.pokeService.setUrl(this.pokeId);

    this.pokeService.capturePokemon().subscribe(
      (data) => {
        console.log(data);

        // tempArray will store the sprites objects
        let tempArray:object;

        this.pokeName = data['name'];
        tempArray = data['sprites'];
        this.pokeImageUrl = tempArray['front_default'];

        // The above is a messy version
      }
    )
  }

  pokeButtonClickedAgain() {
    console.log("Other Poke button works");

    this.pokeService.setUrl(this.pokeId);

    this.pokeService.capturePokemonAgain().subscribe(
      (data) => {
        const ourField = 'front_default';

        this.pokeName = data.name;
        this.pokeImageUrl = data.sprites[ourField];
        // This is a slightly neater version (no temp-array, no strings)
      }
    )
  }

}
