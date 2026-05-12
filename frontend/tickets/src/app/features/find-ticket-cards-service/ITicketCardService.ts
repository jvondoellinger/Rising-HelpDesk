import { Card } from '../../components/card/card';
import { InjectionToken } from '@angular/core';
import { Ticket } from './model/ticket-model';

export interface ITicketCardService {
  fetchTickets(): Promise<Ticket[]>;
}

export const TICKET_CARD_SERVICE = new InjectionToken<ITicketCardService>('TICKET_CARD_SERVICE');
