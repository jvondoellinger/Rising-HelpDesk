package io.github.jvondoellinger.rising_helpdesk.profile.application.queries.permission;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;

public record FindPermissionPaginationQuery(int page, int size)
	implements Query<Pagination<PermissionDetails>> {
}
