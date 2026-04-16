package io.github.jvondoellinger.rising_helpdesk.access_control.application.handlers.query.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.queries.accessprofile.FindAccessProfileByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;

public interface FindAccessProfileByIdQueryHandler extends QueryHandler<FindAccessProfileByIdQuery, AccessProfileDetails> {
}
