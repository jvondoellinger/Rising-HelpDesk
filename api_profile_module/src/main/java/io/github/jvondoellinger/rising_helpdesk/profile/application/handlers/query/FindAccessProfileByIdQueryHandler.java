package io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query;

import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.FindAccessProfileByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;

public interface FindAccessProfileByIdQueryHandler extends QueryHandler<FindAccessProfileByIdQuery, AccessProfileDetails> {
}
