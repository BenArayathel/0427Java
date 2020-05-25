import { Injectable } from '@angular/core';

/*
	Injectable, app-wide message service for sending messages to be displayed.
	We'll be injecting this service into the HeroService.
	Goal: display a message when HeroService fetches successfull!
*/

@Injectable({
	providedIn: 'root', // root makes this service app-wide
})

export class MessageService {

	/* 
		This service exposes its cache of messages and two methods: 
			add() to add a message to the cache
			clear() to clear the cache
	*/

	messages: string[] = []; // this is the "cache"

	add(message: string) {
		this.messages.push(message);
	}

	clear() {
		this.messages = [];
	}
}

// Ready to INJECT into HeroService!