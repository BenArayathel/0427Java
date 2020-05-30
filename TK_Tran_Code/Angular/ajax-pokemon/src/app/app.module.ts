import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AjaxComponent } from './ajax/ajax.component';
import { PokemonService } from './services/pokemon.service';

// ROOT MODULE
@NgModule({
	declarations: [ // register components, directives, and pipes here
		AppComponent,
		AjaxComponent
	],
	imports: [ // register external module imports here
		BrowserModule,
		FormsModule,
		HttpClientModule
	],
	providers: [ // register injectables here
		PokemonService
	],
	bootstrap: [ // components that're automatically bootstrapped when this module is bootstrapped
		AppComponent
	]
})
export class AppModule { }
