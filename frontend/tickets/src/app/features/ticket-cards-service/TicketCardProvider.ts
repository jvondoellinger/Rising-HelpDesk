import { ITicketCardService } from './ITicketCardService';
import { Environment } from '../../enviroment/enviroment';
import { TicketCardsMockService } from './TicketCardsMockService';
import { TicketCardsService } from './TicketCardsService';

export class TicketCardProvider {
  public resolve(): ITicketCardService {
    if (Environment.testing)
      return new TicketCardsMockService();
    return new TicketCardsService();
  }
}
