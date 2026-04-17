package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.userprofile.FindUserProfileByAccessProfileIdQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;

public interface FindUserProfileByAccessProfileIdHandler extends QueryHandler<FindUserProfileByAccessProfileIdQuery, Pagination<UserProfileDetails>> {
}
