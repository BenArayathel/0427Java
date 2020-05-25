import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core'; // different from tutorial
import { FormsModule } from '@angular/forms'; // not included in forms module anymore?

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeroesComponent } from './heroes/heroes.component';
import { HeroDetailComponent } from './hero-detail/hero-detail.component';
import { MessagesComponent } from './messages/messages.component';

// this is the ngModel class
@NgModule({
  declarations: [
    AppComponent,
    HeroesComponent,
    HeroDetailComponent,
    MessagesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  // this sets the main view, and should only be set here
  bootstrap: [AppComponent]
})
export class AppModule { }
