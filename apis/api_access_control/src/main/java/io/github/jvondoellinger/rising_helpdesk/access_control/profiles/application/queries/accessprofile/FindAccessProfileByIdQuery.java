package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.Query;

import java.util.UUID;

public record FindAccessProfileByIdQuery(UUID id) implements Query<AccessProfileDetails> {
}
