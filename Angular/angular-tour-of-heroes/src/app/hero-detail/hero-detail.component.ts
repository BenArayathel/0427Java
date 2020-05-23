import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Hero } from '../hero';
import { HeroService } from '../hero.service';

@Component({
  selector: 'app-hero-detail',
  templateUrl: './hero-detail.component.html',
  styleUrls: ['./hero-detail.component.css']
})
export class HeroDetailComponent implements OnInit {

  @Input() hero: Hero;

  constructor(
    //Injection!
    // ActivatedRoute holds details about the route that lead to this component
    // We'll use it to get the hero id from the route
    private route:ActivatedRoute,
    private heroService:HeroService,
    // Location is used for interacting with the browser.
    // We'll use it to navigate back to the view that brought us here 
    // (dashboard or heroes)
    private location:Location
  ) { }

  ngOnInit():void {
    this.getHero();
  }

  getHero():void {
    // Route parameters are always strings
    // The '+' after the equals converts from string to number!
    const id = +this.route.snapshot.paramMap.get('id');
    this.heroService.getHero(id)
      .subscribe(hero => this.hero = hero);
  }

  save():void {
    this.heroService.updateHero(this.hero)
      .subscribe(() => this.goBack());
  }

  goBack():void {
    this.location.back();
  }

}
