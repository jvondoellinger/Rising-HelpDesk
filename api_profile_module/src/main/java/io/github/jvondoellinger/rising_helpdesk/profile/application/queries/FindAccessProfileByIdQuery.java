package io.github.jvondoellinger.rising_helpdesk.profile.application.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;

public record FindAccessProfileByIdQuery(AccessProfileId id) implements Query<AccessProfileDetails> {
}
