import { Component } from '@angular/core';
import { Animal } from "./animal"

@Component({
  selector: 'app-animal-list',
  templateUrl: './animal-list.component.html',
  styleUrls: ['./animal-list.component.css']
})
export class AnimalListComponent {

  currentLoggedInUser = "Guest";

  imageWidth=200;
  imageMargin=2;
  showImage=true;

  _inputField = '';

  animals:Animal[];
  filteredAnimals:Animal[];

  get inputField() {
    return this._inputField;
  }

  set inputField(input:string) {
    this._inputField = input;

    this.filteredAnimals = this._inputField?
        this.performFilter(this.inputField) : this.animals;
        //        true                      :     false
  }

  constructor() {
    this.animals = [

      new Animal({
        commonName: 'Brown Bear',
        otherNames: ['Grizzly Bear', 'Grizzly'],
        hasFur: true,
        hasScales: false,
        hasFeathers: false,
        hasLungs: true,
        hasGills: false,
        image:"https://www.nationalgeographic.com/content/dam/yourshot/2014/03/3143130.jpg"
      }, {
        kingdom: "Animalia",
        phylum: "Chordata",
        class: "Mammalia",
        order: "Carnivora",
        family: "Ursidae",
        genus: "Ursus",
        speciesEpithet: "arctos"
      }),

      new Animal({
        commonName: 'Clownfish',
        otherNames: [],
        hasFur: false,
        hasScales: true,
        hasFeathers: false,
        hasLungs: false,
        hasGills: true,
        image:"https://www.aquariumofpacific.org/images/made/images/uploads/clownfish_1000_750_80auto_s.jpg"
      }, {
        kingdom: "Animalia",
        phylum: "Chordata",
        class: "Actinopterygii",
        order: "Perciformes",
        family: "Pomacentridae",
        genus: "Amphiprion",
        speciesEpithet: "ocellaris"
      }),

      new Animal({
        commonName: 'Scarlet Macaw',
        otherNames: [],
        hasFur: false,
        hasScales: false,
        hasFeathers: true,
        hasLungs: true,
        hasGills: false,
        image:"https://www.thesprucepets.com/thmb/H6wBs2iN9Gh5shsT2CXoZOhsL88=/3543x2657/smart/filters:no_upscale()/GettyImages-634869043-58a6e83f5f9b58a3c918ca12.jpg"
      }, {
        kingdom: "Animalia",
        phylum: "Chordata",
        class: "Aves",
        order: "Psittaciformes",
        family: "Psittacidae",
        genus: "Ara",
        speciesEpithet: "macao"
      }),
      
      new Animal({
        commonName: 'Boa Constrictor',
        otherNames: ['Red-tailed Boa', "Common Boa"],
        hasFur: false,
        hasScales: true,
        hasFeathers: false,
        hasLungs: true,
        hasGills: false,
        image:"https://res.cloudinary.com/dk-find-out/image/upload/q_80,w_1920,f_auto/Rep1-_101_ytsht7.jpg"
      }, {
        kingdom: "Animalia",
        phylum: "Chordata",
        class: "Reptilia",
        order: "Squamata",
        family: "Boidae",
        genus: "Boa",
        speciesEpithet: "constrictor"
      }),

      new Animal({
        commonName: 'Red-Eyed Tree Frog',
        otherNames: [],
        hasFur: false,
        hasScales: true,
        hasFeathers: false,
        hasLungs: true,
        hasGills: false,
        image:"https://d2a5vhda9v4n4x.cloudfront.net/wp-content/uploads/2018/06/red-eyed-tree-frog-1.jpg"
      }, {
        kingdom: "Animalia",
        phylum: "Chordata",
        class: "Amphibia",
        order: "Anura",
        family: "Phyllomedusidae",
        genus: "Agalychnis",
        speciesEpithet: "callidryas"
      })
    ];

    this.filteredAnimals = this.animals;

    console.log(this.animals);
  }

  toggleImage() {
    // toggles showing the image
    this.showImage = !this.showImage;
  }

  performFilter(filterValue:string):Animal[] {
    filterValue = filterValue.toLocaleLowerCase();

    return this.animals.filter(
      (animal:Animal) => 
      animal.commonName.toLocaleLowerCase().indexOf(filterValue)!==-1
    );
  }

}
