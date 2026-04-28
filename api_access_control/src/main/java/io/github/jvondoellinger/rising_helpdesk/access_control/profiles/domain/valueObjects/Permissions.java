package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.valueObjects;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.kernel.DomainCollection;

import java.util.ArrayList;
import java.util.List;

@Deprecated
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
