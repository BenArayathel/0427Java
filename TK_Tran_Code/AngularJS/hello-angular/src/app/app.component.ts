import { Component } from '@angular/core';

/*
	Components
		A component controls a patch of screen called a "view".
		We define a component's logic (what it does to support a view) inside a class.
		The class interacts with the view through an API of properties and methods.
*/

// selectors for every component
@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent {
	title = 'hello-angular';
}
