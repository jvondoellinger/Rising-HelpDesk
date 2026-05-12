import { Component, inject, AfterViewInit } from '@angular/core';
import { Modal } from 'bootstrap';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CreateTicketModal } from '../create-ticket-modal/create-ticket-modal';

@Component({
  selector: 'app-navbar',
  standalone: true,
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {
  constructor(private modalService: NgbModal) {}

  openModal() {
    const modalRef = this.modalService.open(CreateTicketModal, {
      centered: true,
      backdrop: 'static', // impede fechar clicando fora
      keyboard: true,
      size: 'lg',
      modalDialogClass: 'modal-border-none',
    });

    modalRef.result.then(
      (result) => console.log('Modal fechado com sucesso:', result),
      (reason) => console.log('Modal dispensado:', reason),
    );
  }
}
