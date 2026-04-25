package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.Query;

public record FindAccessProfileByNameQuery(String name) implements Query<AccessProfileDetails> {
}
