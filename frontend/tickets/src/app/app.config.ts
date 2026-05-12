import { ApplicationConfig, importProvidersFrom, inject, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { TICKET_CARD_SERVICE } from './features/find-ticket-cards-service/ITicketCardService';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { provideAnimations } from '@angular/platform-browser/animations';
import { Environment } from './enviroment/enviroment';
import { TicketCardsService } from './features/find-ticket-cards-service/TicketCardsService';
import { TicketCardsMockService } from './features/find-ticket-cards-service/TicketCardsMockService';
import { CREATE_TICKET_SERVICE, CreateTicketService } from './features/create-ticket-service/CreateTicketService';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideClientHydration(withEventReplay()),
    {
      provide: TICKET_CARD_SERVICE,
      useFactory: () => {
        Environment.production
        ? inject(TicketCardsService) : inject(TicketCardsMockService);
      },
    },
    {
      provide: CREATE_TICKET_SERVICE,
      useFactory: () => {
        Environment.production
          ? inject(CreateTicketService) : inject(CreateTicketService);
      },
    },
    provideAnimations(), // importante para o modal
    importProvidersFrom(NgbModule),
  ],
};
