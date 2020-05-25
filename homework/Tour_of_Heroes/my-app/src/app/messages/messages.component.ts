import { Component, OnInit } from '@angular/core';
import { MessageService } from '../messages.service';

// I called mine messageS service, watch out for that

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  // this one is public because it will be 'bound' to the html
  constructor(public messageService: MessageService) { }

  ngOnInit(): void {
  }

}
