import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'; // AppRoutingModule (this class) imports RouterModule and Routes to provide routing functionality.

import { HeroesComponent } from './heroes/heroes.component'; // HeroesComponent imported to give router a destination.
import { DashboardComponent } from './dashboard/dashboard.component'; // DashboardComponent imported to give router another destination.
import { HeroDetailComponent } from './hero-detail/hero-detail.component'; // Another destination.

/* 
	Routes tell the Router which view to display when a user clicks something.
	A typical route has two properties:
		path: a string that matches the URL in the browser address bar.
		component: the component that the route should create when navigating to this route.
*/
const routes: Routes = [
	// Setting default path; navigating to dashboard automatically.
	{ path: '', redirectTo: '/dashboard', pathMatch: 'full' },

	// Setting other paths along with their respective components that'll display.
	{ path: "heroes", component: HeroesComponent }, // (localhost:4200/heroes will display HeroesComponent).
	{ path: "dashboard", component: DashboardComponent }, // (localhost:4200/heroes will display DashboardComponent).
	{ path: 'detail/:id', component: HeroDetailComponent } // a "parameterized" route; matches pattern to the hero detail view. (:) indicates a placeholder for a specific hero ID.
];

// @NgModule initializes the router and makes it listen for browser location changes.
@NgModule({
	imports: [RouterModule.forRoot(routes)], // AppRoutingModule adds RouterModule to the AppRoutingModule imports AND configs it with the routes (all in once step)
	exports: [RouterModule] // AppRoutingModule then exports RouterModule to make it globally available for use.
})
export class AppRoutingModule { }