package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.mappers.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.response.AccessProfileResponse;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.entities.Permission;

import org.springframework.stereotype.Service;

@Service
public class AccessProfileResponseMapper {
	public AccessProfileResponse from(AccessProfileDetails details) {
		var p = details.permissions().stream().map(Permission::getCode).toList();
		return new AccessProfileResponse(details.accessProfileId().toString(), p, details.createdAt(), details.updatedAt());
	}
}