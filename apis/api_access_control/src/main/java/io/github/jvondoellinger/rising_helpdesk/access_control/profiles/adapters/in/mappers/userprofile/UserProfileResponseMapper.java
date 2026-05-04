package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.mappers.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.response.UserProfileResponse;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.UserProfileDetails;
import org.springframework.stereotype.Service;

@Service
public class UserProfileResponseMapper {
	public UserProfileResponse from(UserProfileDetails details) {
		return new UserProfileResponse(details.userId(), details.accessProfile(),details.createdAt(), details.updatedAt());
	}
}