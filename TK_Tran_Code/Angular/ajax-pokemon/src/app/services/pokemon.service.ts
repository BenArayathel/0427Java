import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

/* 
	Service Purpose:
		Utilizes HttpClient to send GET requests to PokeAPI.
*/

@Injectable({
	providedIn: 'root'
})
export class PokemonService {

	/*
		RxJS (Reactive Extension JavaScript)
			A library for "reactive programming" using Observables.
			Observable - represents the idea of an invokable collection of future values or events (kinda like promises).
			Purpose: makes asynchronous and callback functionality simpler.
	*/

	constructor(private HttpClient: HttpClient) {
	}

	private url: string;

	// Appends ID to API URL
	setUrl(pokeID: number) {
		this.url = `https://pokeapi.co/api/v2/pokemon/${pokeID}`;
	}

	// Messy AJAX method; returns an Observable string
	fetchPokemonMessy(): Observable<string> {
		return this.HttpClient.get<string>(this.url); // GET
	}

	// Clean AJAX method, returns an Observable Poke
	fetchPokemonClean(): Observable<Poke> {
		return this.HttpClient.get<Poke>(this.url); // GET
	}
}

export interface Poke {
	"id": number;
	"name": string;
	"sprites": object;
	"base_experience": number;
}