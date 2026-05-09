import { ITicketCardService } from './ITicketCardService';
import { Injectable } from '@angular/core';
import { Queue, Ticket } from './model/ticket-model';

@Injectable()
export class TicketCardsMockService implements ITicketCardService {
  fetchTickets(): Promise<Ticket[]> {
    return Promise.resolve(this.factory());
  }
  public factory(): Ticket[] {
    var tickets: Ticket[] = [];

    for (var i = 0; i < 105; i++) {
      const queue = new Queue(crypto.randomUUID(), 'Suporte', 'TI');

      const ticket = new Ticket(
        crypto.randomUUID(),
        '2384729384',
        'Erro ao fazer login',
        queue,
        new Date(Date.now() + 99999999999), // deadline +1 dia
        ['João', 'Maria'],
        'OPEN',
        new Date(2026, 0, 5),
        new Date(2026, 4, 18),
      );
      tickets.push(ticket);
    }

    return tickets;
  }
}
