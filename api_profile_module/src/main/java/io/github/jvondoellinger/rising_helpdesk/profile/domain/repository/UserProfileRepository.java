package io.github.jvondoellinger.rising_helpdesk.profile.domain.repository;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudsRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository extends CrudsRepository<UserProfile, UUID> {
	Optional<Pagination<UserProfile>> findByAccessProfileId(UUID accessProfileId, PaginationFilter filter);
}
