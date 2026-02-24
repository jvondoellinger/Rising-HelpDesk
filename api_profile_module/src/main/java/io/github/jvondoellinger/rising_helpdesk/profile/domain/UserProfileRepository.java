package io.github.jvondoellinger.rising_helpdesk.profile.domain;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudsRepository;

public interface UserProfileRepository extends CrudsRepository<UserProfile, UserProfileId> {

}
