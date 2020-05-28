import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PlanetListComponent } from './planet-list/planet-list.component';
import { PlanetService } from './shared/planet.service';

import { FormsModule } from '@angular/forms';
import { WelcomeComponent } from './welcome/welcome.component';
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

    RouterModule.forRoot([
      {path: 'welcome', component: WelcomeComponent},
      {path: 'planets/:myVariable', component: PlanetListComponent},
      {path: '', component: WelcomeComponent},
      {path: '**', redirectTo: 'welcome', pathMatch: 'full'}
    ])

  ],
  providers: [//used to declare services
    PlanetService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
