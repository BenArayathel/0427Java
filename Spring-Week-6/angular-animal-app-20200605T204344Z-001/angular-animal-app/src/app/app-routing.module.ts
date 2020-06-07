import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddAnimalComponent } from './add-animal/add-animal.component';
import { AnimalListComponent } from './animal-list/animal-list.component';
import { AnimalEditComponent } from "./animal-edit/animal-edit.component";


const routes: Routes = [
  {path:"addAnimal",component:AddAnimalComponent},
  {path:"listAnimals",component:AnimalListComponent}, // `/editAnimal/${id}`
  {path:"editAnimal/:id",component:AnimalEditComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
