package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;

public record FindAccessProfilePaginationQuery(int page, int size) implements Query<Pagination<AccessProfileDetails>> {
}
