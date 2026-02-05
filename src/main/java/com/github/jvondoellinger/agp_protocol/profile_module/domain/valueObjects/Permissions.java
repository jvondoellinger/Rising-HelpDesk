package com.github.jvondoellinger.agp_protocol.profile_module.domain.valueObjects;

import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainCollection;

import java.util.List;

public class Permissions extends DomainCollection<Permission> {
	public Permissions(List<Permission> permissions) {
		super(permissions);
	}
	public Permissions() {
	}


}
