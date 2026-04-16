package io.github.jvondoellinger.rising_helpdesk.access_control.application.queries.permission;

import io.github.jvondoellinger.rising_helpdesk.access_control.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;

public record FindPermissionByCodeQuery(String code) implements Query<PermissionDetails> {
}
