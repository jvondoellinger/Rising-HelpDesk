package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.shared.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.shared.application.cqrs.Query;

import java.util.UUID;

public record FindUserProfileByAccessProfileIdQuery(UUID accessProfileId, int page, int size)
	   implements Query<Pagination<UserProfileDetails>> {
}
