import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  // Angular only binds to *public* component properties
  // and we want to bind it to the template
  constructor(public messageService:MessageService) { }

  ngOnInit(): void {
  }

}
