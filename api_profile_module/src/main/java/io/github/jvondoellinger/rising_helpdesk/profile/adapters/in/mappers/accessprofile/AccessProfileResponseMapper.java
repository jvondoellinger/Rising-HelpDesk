package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.mappers.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.response.AccessProfileResponse;
import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;

import org.springframework.stereotype.Service;

@Service
public class AccessProfileResponseMapper {
	public AccessProfileResponse from(AccessProfileDetails details) {
		var p = details.permissions().stream().map(Permission::getCode).toList();
		return new AccessProfileResponse(details.accessProfileId().toString(), p, details.createdAt(), details.updatedAt());
	}
}