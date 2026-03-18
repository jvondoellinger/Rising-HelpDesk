package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.mappers;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.response.AccessProfileResponse;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.response.UserProfileResponse;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import org.springframework.stereotype.Service;

@Service
public class UserProfileResponseMapper {
	public UserProfileResponse from(UserProfileDetails details) {
		return new UserProfileResponse(details.userId(), details.accessProfile(),details.createdAt(), details.updatedAt());
	}
}