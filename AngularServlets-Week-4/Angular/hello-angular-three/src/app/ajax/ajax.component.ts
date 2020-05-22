import { Component, OnInit } from '@angular/core';
import { PokemonService } from '../services/pokemon.service';

@Component({
  selector: 'app-ajax',
  templateUrl: './ajax.component.html',
  styleUrls: ['./ajax.component.css']
})
export class AjaxComponent implements OnInit {

  pokeId: number;
  pokeName = '';
  pokeImageUrl = '';

  constructor(private pokeService: PokemonService) { }

  ngOnInit(): void {
  }

  pokeButtonClicked(){
      console.log("button is clicked!");
      console.log(this.pokeId);

      this.pokeService.setUrl(this.pokeId);

      this.pokeService.capturePokemon().subscribe(

        (data) => {
          console.log(data);

          const ourField1 = 'name';
          const ourField2 = 'sprites';
          const ourField3 = 'front_default';

          let spritesArray: object;

          this.pokeName = data[ourField1];
          spritesArray = data[ourField2];
          this.pokeImageUrl = spritesArray[ourField3];

          //THIS IS A MESSY VERSION
        }
      )
  }

  pokeButtonClickedAgain(){

    console.log("Other Poke button works");
    this.pokeService.setUrl(this.pokeId);

    this.pokeService.capturePokemonAgain().subscribe(

      (data) =>{

      const ourField = 'front_default';

      this.pokeName = data.name;
      this.pokeImageUrl = data.sprites[ourField];
      //Neater version
      }
    )
  }

}
