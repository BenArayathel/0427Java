import { Component } from '@angular/core';

// ROOT COMPONENT
@Component({
	selector: 'app-root', // used to select this component in HTML
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent { // component logic defined in its class
	rootTitle: string = "TK's Angular Playground";
}
