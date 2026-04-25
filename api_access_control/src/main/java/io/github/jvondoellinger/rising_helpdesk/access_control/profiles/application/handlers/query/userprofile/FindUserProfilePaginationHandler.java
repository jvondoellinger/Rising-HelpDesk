package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.userprofile.FindUserProfilePaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.QueryHandler;

public interface FindUserProfilePaginationHandler extends QueryHandler<FindUserProfilePaginationQuery, Pagination<UserProfileDetails>> {
}
