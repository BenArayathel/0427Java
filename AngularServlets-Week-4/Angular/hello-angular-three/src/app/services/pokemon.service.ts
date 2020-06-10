import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


export interface Poke {
  'name': string;
  'sprites': object;
  'base_experience': number;
  'id': number;
}


@Injectable({
  providedIn: 'root'
})
export class PokemonService {

  /**
   * What is RXJS?
   *    Reactive Extension Javascript
   *    It contains objects like Observables, BehavioralSubjects. Promises, etc.
   *    RXJS has tools to make asychrnoous and callback functionalities simpler.
   */

  private baseUrl = 'https://pokeapi.co/api/v2/pokemon/';
  private url = '';

  constructor(private httpCli: HttpClient) {
    
  } 

  setUrl(pokeId: number){
    this.url = this.baseUrl + pokeId;
  }

  capturePokemon(): Observable<string> {
    return this.httpCli.get<string>(this.url);
  }

  capturePokemonAgain(): Observable<Poke> {
    return this.httpCli.get<Poke>(this.url);
  }

}
