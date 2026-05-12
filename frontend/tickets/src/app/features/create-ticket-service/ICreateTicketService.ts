import CreateTicketRequest from './CreateTicketRequest';

export default interface ICreateTicketService {
  create(request: CreateTicketRequest): Promise<void>;
}
