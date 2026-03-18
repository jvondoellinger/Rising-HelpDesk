package io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query.permission;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.permission.FindPermissionPaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;

public interface FindPermissionPaginationHandler extends QueryHandler<FindPermissionPaginationQuery, Pagination<PermissionDetails>> {
}
