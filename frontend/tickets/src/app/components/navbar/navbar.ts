import { Component, inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { TicketModal } from '../ticket-modal/ticket-modal';
@Component({
  selector: 'app-navbar',
  standalone: true,
  templateUrl: './navbar.html',
})
export class Navbar {
  private platformId = inject(PLATFORM_ID);

  async openModal() {
    // evita comportamento de link (#)
    event?.preventDefault();

    const bootstrap = await import('bootstrap');

    const el = document.getElementById('app-ticket-modal');

    if (!el) {
      console.error('Modal não encontrado no DOM');
      return;
    }

    // 🔥 destrói instância anterior se existir (resolve backdrop bug)
    const existing = bootstrap.Modal.getInstance(el);
    if (existing) {
      existing.dispose();
    }

    const modal = new bootstrap.Modal(el);
    modal.show();
  }
}
