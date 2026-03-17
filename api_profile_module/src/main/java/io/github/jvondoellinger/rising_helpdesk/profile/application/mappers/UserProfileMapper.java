package io.github.jvondoellinger.rising_helpdesk.profile.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.userprofile.CreateUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.UserProfile;
import org.springframework.stereotype.Service;

@Service
public class UserProfileMapper {
	public UserProfile from(CreateUserProfileCommand command) {
		return new UserProfile(command.accessProfileId());
	}

	public UserProfileDetails details(UserProfile userProfile) {
		return new UserProfileDetails(userProfile.getUserId(), userProfile.getAccessProfile(), userProfile.getCreatedAt(), userProfile.getUpdatedAt());
	}
}
