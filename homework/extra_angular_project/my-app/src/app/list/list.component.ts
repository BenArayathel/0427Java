import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  brews: Object;

  constructor(private _http: HttpService) { }

  // whatever is in here runs when the app is loaded
  ngOnInit() {
    // this._http.myMethod();
    this._http.myBeer().subscribe(data => {
      this.brews = data;
      console.log(this.brews);
      
    })

  }

}
