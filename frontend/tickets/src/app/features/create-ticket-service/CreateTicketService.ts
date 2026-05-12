import { ITicketCardService } from '../find-ticket-cards-service/ITicketCardService';
import { Ticket } from '../find-ticket-cards-service/model/ticket-model';
import CreateTicketRequest from './CreateTicketRequest';
import { firstValueFrom } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import ICreateTicketService from './ICreateTicketService';
import { Injectable } from '@angular/core';

@Injectable()
export class CreateTicketService implements ICreateTicketService {
  private url: string = "http://localhost:8001/api/ticket";
  constructor(private httpClient: HttpClient) {
  }

    async create(request: CreateTicketRequest): Promise<void> {
      await firstValueFrom(
        this.httpClient.post(this.url, request)
      )
    }
}

export const CREATE_TICKET_SERVICE: string = "CREATE_TICKET_SERVICE";
