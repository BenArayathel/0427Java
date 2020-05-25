import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service'; // import MessageService into this component

@Component({
	selector: 'app-messages',
	templateUrl: './messages.component.html',
	styleUrls: ['./messages.component.css']
})

export class MessagesComponent implements OnInit {

	// Modifies constructor to declare a public messageService property
	// Angular will inject the singleton MessageService into that property when it creates the MessagesComponent
	// messageService must be PUBLIC bc we're going to BIND IT TO THE TEMPLATE (Angular only binds to public component properties!!!)
	constructor(public messageService: MessageService) { }

	ngOnInit(): void {
	}

}
