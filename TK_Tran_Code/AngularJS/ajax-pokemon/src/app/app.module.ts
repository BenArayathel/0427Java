import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AjaxComponent } from './ajax/ajax.component';
import { PokemonService } from './services/pokemon.service';

@NgModule({
	declarations: [ // components go here
		AppComponent,
		AjaxComponent
	],
	imports: [ // external modules go here
		BrowserModule,
		FormsModule,
		HttpClientModule
	],
	providers: [ // services go here
		PokemonService
	],
	bootstrap: [
		AppComponent
	]
})
export class AppModule { }
