import { Component, Inject } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import ICreateTicketService from '../../features/create-ticket-service/ICreateTicketService';
import CreateTicketRequest from '../../features/create-ticket-service/CreateTicketRequest';

@Component({
  selector: 'create-ticket-modal',
  standalone: true,
  imports: [],
  templateUrl: './create-ticket-modal.html',
  styleUrls: ['./create-ticket-modal.css'],
})
export class CreateTicketModal {
  private model: CreateTicketRequest | null = null;
  constructor(public activeModal: NgbActiveModal, public service: ICreateTicketService) {}

  create() {
    if (this.model === null) return;
    if (!this.model.title) return; // validação simples

    this.activeModal.close({
      titulo: this.model.title,
      descricao: "TEST",
    });
  }

  close() {
    this.activeModal.dismiss();
  }
}
