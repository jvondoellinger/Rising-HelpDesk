package io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query.permission;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.permission.FindPermissionByCodeQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;

public interface FindPermissionByCodeHandler extends QueryHandler<FindPermissionByCodeQuery, PermissionDetails> {
}
