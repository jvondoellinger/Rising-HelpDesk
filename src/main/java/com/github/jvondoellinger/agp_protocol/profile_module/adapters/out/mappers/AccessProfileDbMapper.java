package com.github.jvondoellinger.agp_protocol.profile_module.adapters.out.mappers;

import com.github.jvondoellinger.agp_protocol.profile_module.domain.AccessProfile;
import com.github.jvondoellinger.agp_protocol.profile_module.infrastructure.AccessProfileDbEntity;
import org.springframework.stereotype.Service;

@Service
public class AccessProfileDbMapper {
	public AccessProfileDbEntity from(AccessProfile accessProfile) {
		return null;
	}

	public AccessProfile toAccessProfile(AccessProfileDbEntity accessProfileDbEntity) {
		return null;
	}
}
