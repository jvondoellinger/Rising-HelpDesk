package io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query;

import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.FindAccessProfileByNameQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;

public interface FindAccessProfileByNameQueryHandler extends QueryHandler<FindAccessProfileByNameQuery, AccessProfileDetails> {
}
