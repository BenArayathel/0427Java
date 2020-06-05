import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddAnimalComponent } from './add-animal/add-animal.component';
import { AnimalListComponent } from './animal-list/animal-list.component';


const routes: Routes = [
  {path:"addAnimal", component:AddAnimalComponent},
  {path:"listAnimals", component:AnimalListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
