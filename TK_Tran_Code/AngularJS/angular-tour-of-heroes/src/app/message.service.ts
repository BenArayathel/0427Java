import { Injectable } from '@angular/core';

/*
	We'll be injecting this service into HeroService.
	Purpose: display a message when a HeroService fetch is successfull!
*/

@Injectable({
	providedIn: 'root', // by default, root makes this service app-wide
})
export class MessageService {

	/* 
		This service exposes its "cache" of messages along with two methods: 
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

// MessageService now ready to inject into HeroService!