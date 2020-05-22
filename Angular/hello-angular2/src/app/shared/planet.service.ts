import { Injectable } from '@angular/core';
import { Planet } from './planet';

@Injectable({
  providedIn: 'root'
})
export class PlanetService {
  getPlanet():Planet[] {
    return [
      {
        name: 'Mercury',
        image: 'https://cdn.mos.cms.futurecdn.net/GA4grWEsUYUqH58cDbRBw8-1200-80.jpg'
      },
      {
        name: 'Venus',
        image: 'https://www.jpl.nasa.gov/spaceimages/images/largesize/PIA00270_hires.jpg'
      },
      {
        name: 'Neptune',
        image: 'https://solarsystem.nasa.gov/system/stellar_items/image_files/90_feature_1600x900_4.jpg'
      }]
  }
}
