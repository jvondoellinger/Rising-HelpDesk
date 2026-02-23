package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.mappers;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.response.AccessProfileResponse;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.AccessProfileDetails;
import org.springframework.stereotype.Service;

@Service
public class AccessProfileResponseMapper {
	public AccessProfileResponse from(AccessProfileDetails details) {
		return new AccessProfileResponse(details.accessProfileId().toString(), details.createdAt(), details.updatedAt());
	}
}
