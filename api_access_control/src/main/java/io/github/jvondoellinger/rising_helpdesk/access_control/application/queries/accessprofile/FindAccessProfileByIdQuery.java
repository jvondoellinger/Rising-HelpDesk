package io.github.jvondoellinger.rising_helpdesk.access_control.application.queries.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;

import java.util.UUID;

public record FindAccessProfileByIdQuery(UUID id) implements Query<AccessProfileDetails> {
}
