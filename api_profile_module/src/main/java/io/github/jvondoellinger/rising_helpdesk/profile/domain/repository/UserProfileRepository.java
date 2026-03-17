package io.github.jvondoellinger.rising_helpdesk.profile.domain;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudsRepository;

import java.util.UUID;

public interface UserProfileRepository extends CrudsRepository<UserProfile, UUID> {

}
