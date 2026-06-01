package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.entities.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.shared.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.shared.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.shared.infrastructure.CrudRepository;

import java.util.UUID;

public interface UserProfileRepository extends CrudRepository<UserProfile, UUID> {
	Pagination<UserProfile> findByAccessProfileId(UUID accessProfileId, PaginationFilter filter);
}
