import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddAnimalComponent } from './add-animal/add-animal.component';
import { AnimalListComponent } from './animal-list/animal-list.component';
import { UpdateAnimalComponent } from './update-animal/update-animal.component';
import { DeleteAnimalComponent } from './delete-animal/delete-animal.component';
import { GetByIdAnimalComponent } from './get-by-id-animal/get-by-id-animal.component';


const routes: Routes = [
  {path:"addAnimal",component:AddAnimalComponent},
  {path:"listAnimals",component:AnimalListComponent},
  {path:"updateAnimal",component:UpdateAnimalComponent},
  {path:"deleteAnimals",component:DeleteAnimalComponent},
  {path:"getbyid",component:GetByIdAnimalComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
