import { Component, Input } from '@angular/core';
import { Ticket } from '../../features/find-ticket-cards-service/model/ticket-model';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [],
  templateUrl: './card.html',
})
export class Card {
  @Input({ required: true })
  ticket!: Ticket;

}
