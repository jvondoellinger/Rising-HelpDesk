package io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.accessprofile.FindAccessProfileByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;

public interface FindAccessProfileByIdQueryHandler extends QueryHandler<FindAccessProfileByIdQuery, AccessProfileDetails> {
}
