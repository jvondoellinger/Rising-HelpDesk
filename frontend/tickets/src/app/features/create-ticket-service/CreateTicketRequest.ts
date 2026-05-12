import { TicketService } from './CreateTicketService';
import { UUID } from 'node:crypto';

export default class CreateTicketRequest {
  constructor(
    public title: string,
    public queueId: UUID,
    public deadline: Date,
  ) {}
};
