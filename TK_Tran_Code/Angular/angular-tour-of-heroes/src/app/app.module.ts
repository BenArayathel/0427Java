import { BrowserModule } from '@angular/platform-browser'; // contains required infrastructure for all Angular apps; included by default.
import { NgModule } from '@angular/core'; // marks a class as an NgModule (root module) and supplies configuration metadata.
import { FormsModule } from '@angular/forms'; // <-- ngModel lives here; contains mechanisms for template-driven forms.
import { AppRoutingModule } from './app-routing.module'; // contains routes and a router outlet where Ang will insert the currently-matched component.
import { HttpClientModule } from '@angular/common/http'; // contains mechanisms for communicating with a remote server over HTTP.

// IN-MEMORY WEB API USED TO SIMULATE A DATA SERVER (Reference: https://github.com/angular/in-memory-web-api)
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService } from './in-memory-data.service';

import { AppComponent } from './app.component'; // the root AppComponent
import { HeroesComponent } from './heroes/heroes.component';
import { HeroDetailComponent } from './hero-detail/hero-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { DashboardComponent } from './dashboard/dashboard.component';


// ROOT MODULE
@NgModule({
	imports: [ // register modules from external imports here
		BrowserModule,
		FormsModule,
		AppRoutingModule,
		HttpClientModule,

		// The HttpClientInMemoryWebApiModule module intercepts HTTP requests and returns simulated server responses.
		// Remove it when a real server is ready to receive requests.
		HttpClientInMemoryWebApiModule.forRoot(InMemoryDataService, { dataEncapsulation: false })
	],
	declarations: [ // register components, directives, and pipes here
		AppComponent,
		DashboardComponent,
		HeroesComponent,
		HeroDetailComponent,
		MessagesComponent,
	],
	providers: [

	],
	bootstrap: [AppComponent]
})
export class AppModule { }