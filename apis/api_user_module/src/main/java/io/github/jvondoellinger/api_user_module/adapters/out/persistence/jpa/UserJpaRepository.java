package io.github.jvondoellinger.api_user_module.adapters.out.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserJpaRepository, UUID> {
}
