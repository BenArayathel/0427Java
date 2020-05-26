import { Component } from '@angular/core';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent {
	// These properties can be accessed/data-binded via interpolation syntax {{}} in the template (HTML) itself.
	title = 'Marvel Cinematic Universe';
}
