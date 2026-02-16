package io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainCollection;

import java.util.List;

public class Permissions extends DomainCollection<Permission> {
	public Permissions(List<Permission> permissions) {
		super(permissions);
	}

	public Permissions() {
	}


}
