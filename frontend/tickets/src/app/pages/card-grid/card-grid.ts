import { Component, Inject } from '@angular/core';
import { Card } from '../../components/card/card';
import { ITicketCardService, TICKET_CARD_SERVICE } from '../../features/find-ticket-cards-service/ITicketCardService';
import { Ticket } from '../../features/find-ticket-cards-service/model/ticket-model';

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
