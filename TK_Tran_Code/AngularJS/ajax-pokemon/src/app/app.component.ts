import { Component } from '@angular/core';

// ROOT COMPONENT
@Component({
	selector: 'app-root', // used to select this component
	templateUrl: './app.component.html', // specifies where the template (HTML) file is located
	styleUrls: ['./app.component.css'] // specifies where the style (CSS) file is located
})
export class AppComponent {
	title = 'Pokemon API: AJAX';
}
