import { Component, OnInit } from '@angular/core';
import { Animal } from './animal';

@Component({
  selector: 'app-animal',
  templateUrl: './animal.component.html',
  styleUrls: ['./animal.component.css']
})
export class AnimalComponent {

  //dealing with images
  imageWidth=100;
  imageMargin=2;
  showImage=false;

  //used to demo 2-way binding in the HTML page
  actualInputField = '';

  get inputField() {
    return this.actualInputField;
  }

  set inputField(temp: string) {
    this.actualInputField = temp;

    // this.filteredAnimals=this.actualInputField?
    //       this.performFilter(this.inputField):this.animals;
          //      true                           false
  }

  animals: Animal[];
  filteredAnimals: Animal[];

  constructor() { 
    [{
      type: 'Cat',
      numberOfLegs: 4,
      averageLifespan: '15 years',
      image: 'https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/other/cat_relaxing_on_patio_other/1800x1200_cat_relaxing_on_patio_other.jpg'
    },
    {
      type: 'Dog',
      numberOfLegs: 4,
      averageLifespan: '13 years',
      image: 'https://s3.amazonaws.com/cdn-origin-etr.akc.org/wp-content/uploads/2017/11/11124331/Bernese-Mountain-Dog-On-White-01.jpg'
    },
    {
      type: 'Goat',
      numberOfLegs: 4,
      averageLifespan: '16 years',
      image: 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Hausziege_04.jpg/1200px-Hausziege_04.jpg'
    }];
  }



}
