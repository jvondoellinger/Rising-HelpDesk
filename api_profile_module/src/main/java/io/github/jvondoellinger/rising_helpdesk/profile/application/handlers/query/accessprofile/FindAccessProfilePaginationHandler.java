package io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.accessprofile.FindAccessProfilePaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.userprofile.FindUserProfilePaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;

public interface FindAccessProfilePaginationHandler extends QueryHandler<FindAccessProfilePaginationQuery, Pagination<AccessProfileDetails>> {
}
