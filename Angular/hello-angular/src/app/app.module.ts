import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { PlanetListComponent } from './planet-list/planet-list.component';
import { PrependPipe } from './prepend.pipe';
import { MagicpipePipe } from './shared/magicpipe.pipe';

// app.module.ts is to Angular as pom.xml is to Maven

// decorators are the same thing as annotations (in Java)
@NgModule({
  declarations: [
    //used to declare components and pipes
    AppComponent,
    PlanetListComponent,
    PrependPipe,
    MagicpipePipe
  ],
  imports: [
    // used to import external modules
    // we're using FormsModule for two-way binding
    BrowserModule,
     FormsModule
  ],
  providers: [
    // used to declare services
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
