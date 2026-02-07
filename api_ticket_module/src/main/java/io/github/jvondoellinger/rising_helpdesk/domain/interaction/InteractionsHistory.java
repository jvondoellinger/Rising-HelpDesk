package io.github.jvondoellinger.rising_helpdesk.domain.interaction;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainCollection;

import java.util.List;

public class InteractionsHistory extends DomainCollection<Interaction> {
	public InteractionsHistory(List<Interaction> ids) {
		super(ids);
	}
	public InteractionsHistory() {
	}
}
