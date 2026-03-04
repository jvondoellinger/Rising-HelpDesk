package io.github.jvondoellinger.rising_helpdesk.profile.application.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;

public record FindAccessProfileByNameQuery(String name) implements Query<AccessProfileDetails> {
}
