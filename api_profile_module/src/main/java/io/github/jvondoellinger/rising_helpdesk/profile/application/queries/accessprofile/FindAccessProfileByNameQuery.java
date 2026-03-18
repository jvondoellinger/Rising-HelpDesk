package io.github.jvondoellinger.rising_helpdesk.profile.application.queries.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;

public record FindAccessProfileByNameQuery(String name) implements Query<AccessProfileDetails> {
}
