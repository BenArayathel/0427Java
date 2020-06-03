import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PlanetListComponent } from './planet-list/planet-list.component';
import { PlanetService } from './shared/planet.service';
import { WelcomeComponent } from './welcome/welcome.component';

import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    AppComponent,
    PlanetListComponent,
    WelcomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    /*
    To use the router:
        import RouterModule, define paths, place <router-outlet> in a html
        file, and THEN the router is ready. 

        Simply use the routerLink
    */

    RouterModule.forRoot([
      {path: 'welcome', component: WelcomeComponent},
      {path: 'planets/:myVariable', component: PlanetListComponent},
      {path: 'planets', component: PlanetListComponent},
      {path: '', component: WelcomeComponent},
      {path: '**', redirectTo: 'welcome', pathMatch: 'prefix'}
    ])

  ],
  providers: [//used to declare services
      PlanetService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
