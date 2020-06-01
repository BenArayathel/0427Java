import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { PlanetListComponent } from './planet-list/planet-list.component';
import { PrependPipe } from './prepend.pipe';

// Every Angular app has at least one NgModule class, the "root module".
@NgModule({
	declarations: [ 
		// used to declare components, directives, and pipes
		AppComponent,
		PlanetListComponent,
		PrependPipe
	],
	imports: [
		// used to import external modules; we're adding FormsModule for two-way binding
		BrowserModule, FormsModule
	],
	providers: [ // used to declare services

	],
	// The main application view, the "root component" which hosts all other app views.
	// Only one module, the "root module", can set this boostrap property.
	bootstrap: [AppComponent] 
})
export class AppModule { }
