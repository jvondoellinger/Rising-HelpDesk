package io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainCollection;

import java.util.ArrayList;
import java.util.List;

public class Permissions extends DomainCollection<Permission> {
	public Permissions(List<Permission> permissions) {
		super(permissions);
	}

	public Permissions() {
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Permissions)) {
			return false;
		}
		var p = (Permissions) obj;
		var clone = new ArrayList<>(p.values);
		var isDistinct = clone.stream()
			   .allMatch(permission -> super.values.get(0).equals(permission));
		return isDistinct;
	}
}
