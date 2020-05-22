import { Component, OnInit } from '@angular/core';
import { Food } from './food';

// Assignment:
// 	Create a table structure similar to what has been shown. 
// 	Use a different model (with an image, name and 2 more attributes)
// 	Add more columns corresponding to the model attributes
// 	Utilise at least 2 pipes with different transforms

@Component({
  selector: 'app-my-table',
  templateUrl: './my-table.component.html',
  styleUrls: ['./my-table.component.css']
})
export class MyTableComponent {

    //dealing with images
    imageWidth = 100;
    imageHeight = 100;
    imageMargin = 2;
    showImage = false;
    showEat = true;
  
    //used to demo 2-way binding in the HTML page
    actualInputfield = '';
  
    // SETTERS and GETTERS
    get inputField() {
        return this.actualInputfield;
    }

    // this might not work if i use a number
    set inputField(temp: string) {
      this.actualInputfield = temp;
      
      this.filteredFoods = this.actualInputfield?
          this.performFilter(this.inputField) : this.foods;
          // true false
    }
  
    // this creates two strongly typed ts variables, filled with the values from my model
    foods: Food[];
    filteredFoods: Food[];

  constructor() {
    this.foods = [
      {
        name: 'Apple',
        description: 'Red Fruit',
        info: 'https://www.fooducate.com/product/Apple-With-Skin/FF2857CC-1FA0-11E3-A74D-1E047F0525AB',
        calories: 10,
        image: 'https://images.unsplash.com/photo-1568702846914-96b305d2aaeb?ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60/GA4grWEsUYUqH58cDbRBw8-1200-80.jpg',
      },
      {
        name: 'Burger',
        description: 'Meat sandwhich',
        info: 'https://www.fooducate.com/product/Hamburger-Plain/B7C18C26-1FA2-11E3-A74D-1E047F0525AB',
        calories: 900,
        image: 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60://cdn.mos.cms.futurecdn.https://images.unsplash.com/photo-1568702846914-96b305d2aaeb?ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60/GA4grWEsUYUqH58cDbRBw8-1200-80.jpg',
      },
      {
        name: 'Shake',
        description: 'Ice cream drink',
        info: 'https://www.fooducate.com/product/Burger-King-Chocolate-Milkshake/53273D8D-2230-0DE5-477D-86E922D8F8A0',
        calories: 400,
        image: 'https://images.unsplash.com/photo-1553787499-6f9133860278?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=600&q=60.futurecdn.https://images.unsplash.com/photo-1568702846914-96b305d2aaeb?ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60/GA4grWEsUYUqH58cDbRBw8-1200-80.jpg'
      },
    ];

    this.filteredFoods = this.foods;

  }

    // for the button, an on/off switch
    toggleImage() {
        this.showImage = !this.showImage;
    }

    toggleEat() {
      this.showEat = !this.showEat;
  }

    // this does what?
    performFilter(filterValue: string): Food[] {
      filterValue = filterValue.toLocaleLowerCase();

      return this.foods.filter(
          (food: Food) =>
          food.name.toLocaleLowerCase().indexOf(filterValue)!== -1
    );
  }
}


