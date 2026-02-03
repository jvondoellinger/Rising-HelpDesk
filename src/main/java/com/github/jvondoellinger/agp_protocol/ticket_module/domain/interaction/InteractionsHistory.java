package com.github.jvondoellinger.agp_protocol.ticket_module.domain.interaction;

import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainCollection;

import java.util.List;

public class InteractionsHistory extends DomainCollection<Interaction> {
	public InteractionsHistory(List<Interaction> ids) {
		super(ids);
	}
	public InteractionsHistory() {
	}
}
