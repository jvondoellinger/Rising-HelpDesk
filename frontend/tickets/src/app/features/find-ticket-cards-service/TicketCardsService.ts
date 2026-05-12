import { Card } from '../../components/card/card';
import { ITicketCardService, TICKET_CARD_SERVICE } from './ITicketCardService';
import { Injectable } from '@angular/core';
import { Queue, Ticket } from './model/ticket-model';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';

@Injectable()
export class TicketCardsService implements ITicketCardService {
  private url = 'http://127.0.0.1:8001/api/ticket';
  constructor(private http: HttpClient) {}

  async fetchTickets(): Promise<Ticket[]> {
    return await firstValueFrom(this.http.get<Ticket[]>(this.url));
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
