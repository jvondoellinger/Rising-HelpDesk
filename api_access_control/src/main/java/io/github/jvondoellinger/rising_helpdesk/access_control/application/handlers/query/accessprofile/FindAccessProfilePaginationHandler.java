package io.github.jvondoellinger.rising_helpdesk.access_control.application.handlers.query.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.queries.accessprofile.FindAccessProfilePaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;

public interface FindAccessProfilePaginationHandler extends QueryHandler<FindAccessProfilePaginationQuery, Pagination<AccessProfileDetails>> {
}
