package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.accessprofile.FindAccessProfilePaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;

public interface FindAccessProfilePaginationHandler extends QueryHandler<FindAccessProfilePaginationQuery, Pagination<AccessProfileDetails>> {
}
