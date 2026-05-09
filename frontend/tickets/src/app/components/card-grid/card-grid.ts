import { Component, Inject } from '@angular/core';
import {Card} from '../card/card';
import { ITicketCardService } from '../../features/ticket-cards-service/ITicketCardService';
import { TICKET_CARD_SERVICE } from '../../features/ticket-cards-service/ITicketCardService';
import { Ticket } from '../../features/ticket-cards-service/model/ticket-model';

@Component({
  selector: 'app-card-grid',
  standalone: true,
  imports: [Card],
  templateUrl: './card-grid.html',
})
export class CardGrid {
  items: Ticket[] = [];
  constructor(@Inject(TICKET_CARD_SERVICE) private service: ITicketCardService) {
    this.loadCards();
  }

  protected loadCards() {
    this.service.fetchTickets().then((tickets) => {
      this.items = tickets;
    });
  }
}
