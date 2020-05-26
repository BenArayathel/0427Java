import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

// An interface in clean AJAX method
export interface Poke {
	"id": number;
	"name": string;
	"sprites": object;
	"base_experience": number;
}

@Injectable({
	providedIn: 'root'
})
export class PokemonService {

	/*
	RxJS
		"Reactive Extension JavaScript"
		Contains objs like Observables, BehavioralSubjets, Promises, etc..
		Purpose: has tools to make asynchronous and callback functionality simpler.
	*/

	constructor(private HttpClient: HttpClient) {

	}

	private baseURL = "https://pokeapi.co/api/v2/pokemon/";
	private url = "";

	// Appends ID to URL
	setUrl(PokeID: number) {
		this.url = this.baseURL + PokeID;
	}

	// Messy method
	capturePokemonMessy(): Observable<string> {
		return this.HttpClient.get<string>(this.url);
	}

	// Clean method
	capturePokemonClean(): Observable<Poke> {
		return this.HttpClient.get<Poke>(this.url);
	}
}
