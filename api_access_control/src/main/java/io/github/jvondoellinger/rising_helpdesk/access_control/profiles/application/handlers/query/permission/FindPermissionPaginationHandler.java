package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.permission;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.permission.FindPermissionPaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.QueryHandler;

public interface FindPermissionPaginationHandler extends QueryHandler<FindPermissionPaginationQuery, Pagination<PermissionDetails>> {
}
