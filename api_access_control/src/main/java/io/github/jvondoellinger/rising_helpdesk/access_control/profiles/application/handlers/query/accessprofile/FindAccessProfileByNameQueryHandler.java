package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.accessprofile.FindAccessProfileByNameQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.QueryHandler;

public interface FindAccessProfileByNameQueryHandler extends QueryHandler<FindAccessProfileByNameQuery, AccessProfileDetails> {
}
