import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';


export interface Poke {
  'name':string;
  'sprites':object;
  'base_Experience':number;
  'id':number;
}


@Injectable({
  providedIn: 'root'
})
export class PokemonService {

  /**
   * What is RXJS?
   *  - Reactive Extension JavaScript
   *  - It contains objects like Observable, BehavioralSubjects, Promises, etc.
   *  - RXJS has tools to make asynchronous and callback functionalities simplier
   */

  private baseURL = "https://pokeapi.co/api/v2/pokemon/"
  private url = '';

  constructor(private httpCli:HttpClient) { }

  setUrl(pokeId:number) {
    this.url = this.baseURL+pokeId;
  }

  capturePokemon():Observable<string> {
    // very easy syntax compared to straight JS
    return this.httpCli.get<string>(this.url);
  }

  capturePokemonAgain():Observable<Poke> {
    return this.httpCli.get<Poke>(this.url);
  }
}
