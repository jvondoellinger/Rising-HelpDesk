package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.Query;

public record FindUserProfilePaginationQuery(int page, int size) implements Query<Pagination<UserProfileDetails>> {
}
