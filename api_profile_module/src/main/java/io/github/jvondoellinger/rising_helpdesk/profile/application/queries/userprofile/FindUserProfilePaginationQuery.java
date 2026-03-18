package io.github.jvondoellinger.rising_helpdesk.profile.application.queries.userprofile;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;

public record FindUserProfilePaginationQuery(int page, int size) implements Query<Pagination<UserProfileDetails>> {
}
