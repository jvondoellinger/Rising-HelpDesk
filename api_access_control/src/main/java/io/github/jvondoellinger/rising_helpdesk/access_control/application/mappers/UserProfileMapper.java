package io.github.jvondoellinger.rising_helpdesk.access_control.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.access_control.application.commands.userprofile.CreateUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.domain.entities.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import org.springframework.stereotype.Service;

@Service
public class UserProfileMapper {
	public UserProfile from(CreateUserProfileCommand command) {
		return new UserProfile(command.userId(), command.accessProfileId());
	}

	public UserProfileDetails details(UserProfile userProfile) {
		return new UserProfileDetails(userProfile.getUserId(), userProfile.getAccessProfile(), userProfile.getCreatedAt(), userProfile.getUpdatedAt());
	}

	public Pagination<UserProfileDetails> detailsPagination(Pagination<UserProfile> userProfilePagination) {
		var items = userProfilePagination.items()
			   .stream()
			   .map(this::details)
			   .toList();
		return Pagination.of(items, userProfilePagination.page(), userProfilePagination.totalPages());
	}
}
