package io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query.userprofile;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.userprofile.FindUserProfilePaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;

public interface FindUserProfilePaginationHandler extends QueryHandler<FindUserProfilePaginationQuery, Pagination<UserProfileDetails>> {
}
