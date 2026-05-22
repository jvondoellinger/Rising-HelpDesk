package io.github.jvondoellinger.api_user_module.domain;

import io.github.jvondoellinger.rising_helpdesk.kernel.infrastructure.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
