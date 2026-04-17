package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.userprofile.FindUserProfileByUserIdQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;

public interface FindUserProfileByUserIdHandler extends QueryHandler<FindUserProfileByUserIdQuery, UserProfileDetails> {
}
