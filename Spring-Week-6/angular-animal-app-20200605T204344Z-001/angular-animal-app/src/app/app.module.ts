import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddAnimalComponent } from './add-animal/add-animal.component';
import { AnimalListComponent } from './animal-list/animal-list.component';
import { AnimalService } from './animal.service';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { UpdateAnimalComponent } from './update-animal/update-animal.component';
import { DeleteAnimalComponent } from './delete-animal/delete-animal.component';
import { GetByIdAnimalComponent } from './get-by-id-animal/get-by-id-animal.component';
@NgModule({
  declarations: [
    AppComponent,
    AddAnimalComponent,
    AnimalListComponent,
    UpdateAnimalComponent,
    DeleteAnimalComponent,
    GetByIdAnimalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [AnimalService],
  bootstrap: [AppComponent]
})
export class AppModule { }
