package io.github.jvondoellinger.rising_helpdesk.access_control.application.queries.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;

import java.util.UUID;

public record FindUserProfileByUserIdQuery(UUID userId)
	implements Query<UserProfileDetails> {
}
