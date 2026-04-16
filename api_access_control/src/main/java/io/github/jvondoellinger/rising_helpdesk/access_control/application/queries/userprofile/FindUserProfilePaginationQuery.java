package io.github.jvondoellinger.rising_helpdesk.access_control.application.queries.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;

public record FindUserProfilePaginationQuery(int page, int size) implements Query<Pagination<UserProfileDetails>> {
}
