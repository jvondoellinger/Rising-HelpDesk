package io.github.jvondoellinger.rising_helpdesk.access_control.application.handlers.query.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.queries.userprofile.FindUserProfileByAccessProfileIdQuery;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;

public interface FindUserProfileByAccessProfileIdHandler extends QueryHandler<FindUserProfileByAccessProfileIdQuery, Pagination<UserProfileDetails>> {
}
