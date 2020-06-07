import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Animal } from './animal';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AnimalService {

  private url:string;
  constructor(private http:HttpClient) { 
    this.url="http://localhost:9000/";
  }

  /**
   * http://localhost:9000/animals
   * http://localhost:9000/animal
   * http://localhost:9000/animal
   * http://localhost:9000/animal/5
   * http://localhost:9000/animal/3
   * http://localhost:9000/animal/age/555 
   */

  public createAnimal(animal:Animal){
    return this.http.post<Animal>(this.url+"animal",animal);
  }

  public getAllAnimals():Observable<Animal[]>{
    return this.http.get<Animal[]>(this.url+"animals");
  }

  public getAnimalById( id: number ){
    return this.http.get<Animal>(this.url+"animal/" + id);
  }

  public updateAnimal(animal : Animal){
    return this.http.put<Animal>(this.url+"animal", animal);
  }

  public deleteAnimal(id: number){
    return this.http.delete(this.url+"animal/" + id);
  }

}
