package io.github.jvondoellinger.rising_helpdesk.access_control.application.handlers.query.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.queries.accessprofile.FindAccessProfileByNameQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;

public interface FindAccessProfileByNameQueryHandler extends QueryHandler<FindAccessProfileByNameQuery, AccessProfileDetails> {
}
