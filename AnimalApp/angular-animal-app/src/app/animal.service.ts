import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Animal } from './animal';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnimalService {

  private url:string;

  constructor(private http:HttpClient) {
    this.url = "http://localhost:8080/";
  }

  public createAnimal(animal:Animal) {
    return this.http.post<Animal>(this.url+"animal", animal);
  }

  public updateAnimal(animal:Animal) {
    return this.http.put<Animal>(this.url+"animal", animal);
  }

  public getAllAnimals():Observable<Animal[]> {
    return this.http.get<Animal[]>(this.url+"animals");
  }

}
