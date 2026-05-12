import { ITicketCardService } from './ITicketCardService';
import { Environment } from '../../enviroment/enviroment';
import { TicketCardsMockService } from './TicketCardsMockService';
import { TicketCardsService } from './TicketCardsService';

class TicketCardProvider {
  constructor(private readonly ticketCardsService: TicketCardsService) {}

/*  public resolve(): ITicketCardService {
    if (Environment.testing)
      return new TicketCardsMockService();
    return new TicketCardsService();
  }*/
}

export default TicketCardProvider;
