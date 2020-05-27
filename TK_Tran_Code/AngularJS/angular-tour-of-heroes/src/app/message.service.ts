import { Injectable } from '@angular/core';

/*
	Service purpose: 
		Will be injected into HeroService
		Will display a message when HeroService fetch is successful
*/

@Injectable({
	providedIn: 'root',
})
export class MessageService {

	/* 
		This service exposes its "cache" of messages as well as two methods: 
			add() to add messages to the cache
			clear() to clear the cache
	*/

	messages: string[] = []; // this is the "cache", a string[] array

	add(message: string) {
		this.messages.push(message);
	}

	clear() {
		this.messages = [];
	}
}

// MessageService now ready to inject into HeroService!