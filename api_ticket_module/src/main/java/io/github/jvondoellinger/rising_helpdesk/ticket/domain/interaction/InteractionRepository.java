package io.github.jvondoellinger.rising_helpdesk.ticket.domain.interaction;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.InteractionId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudsRepository;

public interface InteractionRepository extends CrudsRepository<Interaction, InteractionId> {
}
