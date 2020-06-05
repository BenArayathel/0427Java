import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddAnimalComponent } from './add-animal/add-animal.component';
import { AnimalListComponent } from './animal-list/animal-list.component';
import { AnimalService } from './animal.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    AddAnimalComponent,
    AnimalListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    AnimalService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
