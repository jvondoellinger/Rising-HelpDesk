package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.permission;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.permission.FindPermissionByCodeQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.QueryHandler;

public interface FindPermissionByCodeHandler extends QueryHandler<FindPermissionByCodeQuery, PermissionDetails> {
}
