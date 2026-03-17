package io.github.jvondoellinger.rising_helpdesk.profile.domain.repository;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudsRepository;

import java.util.UUID;

public interface UserProfileRepository extends CrudsRepository<UserProfile, UUID> {

}
