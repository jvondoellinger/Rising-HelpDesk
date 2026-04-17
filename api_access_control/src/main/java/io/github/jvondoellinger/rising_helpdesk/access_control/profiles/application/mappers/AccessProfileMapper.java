package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import org.springframework.stereotype.Service;

@Service
public class AccessProfileMapper {
	public AccessProfile from(CreateAccessProfileCommand command)  {
		var permissions = command.permissions()
			   .stream()
			   .map(x -> new Permission(x, null, null))
			   .toList();
		return new AccessProfile(command.name(), permissions);
	}

	public AccessProfileDetails details(AccessProfile accessProfile) {
		return new AccessProfileDetails(accessProfile.getId(), accessProfile.getPermissions(), accessProfile.getCreatedAt(), accessProfile.getUpdatedAt());
	}

	public Pagination<AccessProfileDetails> detailsPagination(Pagination<AccessProfile> accessProfilePagination) {
		var list = accessProfilePagination.items()
			   .stream()
			   .map(this::details)
			   .toList();

		return Pagination.of(list,
			   accessProfilePagination.page(),
			   accessProfilePagination.totalPages());
	}
}