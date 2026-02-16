package io.github.jvondoellinger.rising_helpdesk.ticket.domain.mention;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainCollection;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;

import java.util.List;

public class Mentions extends DomainCollection<UserProfileId> {
	public Mentions(List<UserProfileId> profiles) {
	}
	public Mentions() {
	}


}
