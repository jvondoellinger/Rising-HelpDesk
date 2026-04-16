package io.github.jvondoellinger.rising_helpdesk.access_control.domain.repository;

import io.github.jvondoellinger.rising_helpdesk.access_control.domain.entities.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudRepository;

import java.util.UUID;

public interface UserProfileRepository extends CrudRepository<UserProfile, UUID> {
	Pagination<UserProfile> findByAccessProfileId(UUID accessProfileId, PaginationFilter filter);
}
