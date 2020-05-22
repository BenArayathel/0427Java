import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TestComponent } from './test/test.component';
import { PrependPipe } from './prepend.pipe';
import { MagicpipePipe } from './shared/magicpipe.pipe';
import { PlayerListComponent } from './player-list/player-list.component';
import { CappipePipe } from './shared/cappipe.pipe';

//decorators same as annotations 
@NgModule({
  declarations: [ //used to declare components and pipes
    AppComponent,
    TestComponent,
    PrependPipe,
    MagicpipePipe,
	CappipePipe,
    PlayerListComponent
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
