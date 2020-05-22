import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { PlanetListComponent } from './planet-list/planet-list.component';
import { TestComponent } from './test/test.component';
import { PrependPipe } from './prepend.pipe';
import { MagicpipePipe } from './shared/magicpipe.pipe';
import { MyownComponent } from './myown/myown.component';

//decorators same as annotations 
@NgModule({
  declarations: [ //used to declare components and pipes
    AppComponent,
    PlanetListComponent,
    TestComponent,
    PrependPipe,
    MagicpipePipe,
    MyownComponent
  ],
  imports: [ //used to import external modules 
    //we're using formsmodule for two way binding 
    BrowserModule, FormsModule
  ],
  providers: [
    //used to declared services 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
