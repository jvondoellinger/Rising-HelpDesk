import { Card } from '../../components/card/card';
import { ITicketCardService } from './ITicketCardService';
import { Injectable } from '@angular/core';
import { Queue, Ticket } from './model/ticket-model';

@Injectable()
export class TicketCardsService implements ITicketCardService {
  fetchTickets(): Promise<Ticket[]> {
    return Promise.resolve(this.factory());
  }
  public factory(): Ticket[] {
    var cards: Ticket[] = [];

    for (var i = 0; i < 2; i++) {
      const queue = new Queue(crypto.randomUUID(), 'Suporte', 'TI');

      const ticket = new Ticket(
        crypto.randomUUID(),
        '# 50123023423',
        'Erro ao fazer login',
        queue,
        new Date(Date.now() + 86400000), // deadline +1 dia
        ['João', 'Maria'],
        'OPEN',
        new Date(),
        new Date()
      );
    }

    return cards;
  }
}
