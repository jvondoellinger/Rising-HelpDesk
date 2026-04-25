package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.permission;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.Query;

public record FindPermissionPaginationQuery(int page, int size)
	implements Query<Pagination<PermissionDetails>> {
}
