package io.github.jvondoellinger.rising_helpdesk.ticket.domain.interaction;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainCollection;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Interaction;

import java.util.List;

public class InteractionsHistory extends DomainCollection<Interaction> {
	public InteractionsHistory(List<Interaction> ids) {
		super(ids);
	}
	public InteractionsHistory() {
	}
}
