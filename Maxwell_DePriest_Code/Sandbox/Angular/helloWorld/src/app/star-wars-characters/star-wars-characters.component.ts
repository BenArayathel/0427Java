import { Component, OnInit } from '@angular/core';
import { Character } from './character';

@Component({
  selector: 'app-star-wars-characters',
  templateUrl: './star-wars-characters.component.html',
  styleUrls: ['./star-wars-characters.component.css']
})
export class StarWarsCharactersComponent {

  imageWidth = 100;
  imageMargin = 2;
  showImage = false;
  filteredCharacters: Character[];
  characters:  Character[];

  actualInputfield = '';

  get inputField() {
    return this.actualInputfield;
  }
  set inputField(temp: string){
    this.actualInputfield = temp;

    this.filteredCharacters = this.actualInputfield ? this.performFilter(this.inputField) : this.characters;
            //                                                true                            false
  }


  constructor() {
    this.characters = [
      {
        name: 'Luke Skywalker',
        planet: 'Tatooine',
        species: 'Human',
        forceSensitive: 'Yes'
      },
      {
        name: 'Han Solo',
        planet: 'Coruscant',
        species: 'Human',
        forceSensitive: 'No'
      },
      {
        name: 'Leia Organa',
        planet: 'Alderan',
        species: 'Human',
        forceSensitive: 'Yes'
      },
      {
        name: 'Chewbacca',
        planet: 'Kashyyyk',
        species: 'Wookie',
        forceSensitive: 'No'
      },
      {
        name: 'Jar Jar Binks',
        planet: 'Naboo',
        species: 'Gungan',
        forceSensitive: 'Maybe'
      },
      {
        name: 'Boba Fett',
        planet: 'Kamino',
        species: 'Mandalorian',
        forceSensitive: 'No'
      }

    ]

    this.filteredCharacters = this.characters;

   }

   toggleVader() {
    let vaderRow = document.getElementById("vader-row");
    let vaderPic = document.getElementById("vader-pic");
    vaderRow.hidden = vaderRow.hidden ? false : true;
    vaderPic.hidden = vaderPic.hidden ? false : true;



    
   }


  performFilter(filterValue: string): Character[] {
    filterValue = filterValue.toLocaleLowerCase();

    return this.characters.filter(
      (character: Character) =>
      character.name.toLocaleLowerCase().indexOf(filterValue)!== -1
    );
  }
}
