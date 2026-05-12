import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTicketModal } from './create-ticket-modal';

describe('CreateTicketModal', () => {
  let component: CreateTicketModal;
  let fixture: ComponentFixture<CreateTicketModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateTicketModal],
    }).compileComponents();

    fixture = TestBed.createComponent(CreateTicketModal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
