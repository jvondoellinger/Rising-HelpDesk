package io.github.jvondoellinger.rising_helpdesk.profile.application.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;

import java.util.UUID;

public record FindAccessProfileByIdQuery(UUID id) implements Query<AccessProfileDetails> {
}
