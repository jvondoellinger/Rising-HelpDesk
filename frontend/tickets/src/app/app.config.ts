import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { TICKET_CARD_SERVICE } from './features/ticket-cards-service/ITicketCardService';
import { TicketCardProvider } from './features/ticket-cards-service/TicketCardProvider';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideClientHydration(withEventReplay()),

    {
      provide: TICKET_CARD_SERVICE,
      useFactory: () => {
        return new TicketCardProvider().resolve();
      },
    },
  ]
};
