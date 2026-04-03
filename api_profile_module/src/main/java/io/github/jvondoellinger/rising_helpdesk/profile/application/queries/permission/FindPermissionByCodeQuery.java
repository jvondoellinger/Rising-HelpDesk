package io.github.jvondoellinger.rising_helpdesk.profile.application.queries.permission;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;

public record FindPermissionByCodeQuery(String code) implements Query<PermissionDetails> {
}
