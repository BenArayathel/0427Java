import { Component, OnInit } from '@angular/core';
import { Player } from "./player";
import { Position } from "./position";

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css']
})
export class PlayerListComponent {
	players: Player[];
	filteredPlayers: Player[];
	
	imageWidth = 120;
	imageHeight = 180;
	imageMargin = 2;
	showImage = false;
	
	actualInputField = '';
	
	get inputField() {
		return this.actualInputField;
	}
	
	set inputField(temp: string) {
		this.actualInputField = temp;
		
		this.filteredPlayers = this.actualInputField?
			this.performFilter(this.inputField) : this.players;
	}

  constructor() {
	this.players = [
		{
			name: "Nelson Cruz",
			position: Position.DH,
			ba: 0.311,
			ops: 1.031,
			slg: 0.603,
			hr: 41,
			image: "https://d3k2oh6evki4b7.cloudfront.net/req/202001270/images/headshots/f/fea2f131_mlbam.jpg"
		},
		{
			name: "Max Kepler",
			position: Position.RIGHTFIELD,
			ba: 0.2520,
			slg: 0.519,
			ops: 0.855,
			hr: 36,
			image: "https://d3k2oh6evki4b7.cloudfront.net/req/202001270/images/headshots/c/c9413e23_mlbam.jpg"
		},
		{
			name: "Mike Trout",
			position: Position.CENTERFIELD,
			ba: 0.2910,
			slg: 0.645,
			ops: 1.083,
			hr: 45,
			image: "https://d3k2oh6evki4b7.cloudfront.net/req/202001270/images/headshots/f/f322d40f_mlbam.jpg"
		},
	];
	
	this.filteredPlayers = this.players;
	}
	
	toggleImage() {
		this.showImage = !this.showImage;
	}
	
	performFilter(filterValue: string): Player[] {
	filterValue = filterValue.toLocaleLowerCase();

     return this.players.filter(
       (player: Player) =>
       player.name.toLocaleLowerCase().indexOf(filterValue)!== -1
     );
	}

}
