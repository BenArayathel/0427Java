import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- ngModel lives here.

import { AppComponent } from './app.component';
import { HeroesComponent } from './heroes/heroes.component';
import { HeroDetailComponent } from './hero-detail/hero-detail.component';
import { MessagesComponent } from './messages/messages.component';

// This is the ROOT MODULE
@NgModule({
	declarations: [ // all components must be declared here
		AppComponent,
		HeroesComponent,
		HeroDetailComponent,
		MessagesComponent
	],
	imports: [ // all external imports here
		BrowserModule,
		FormsModule
	],
	providers: [

	],
	bootstrap: [AppComponent]
})

export class AppModule { }
