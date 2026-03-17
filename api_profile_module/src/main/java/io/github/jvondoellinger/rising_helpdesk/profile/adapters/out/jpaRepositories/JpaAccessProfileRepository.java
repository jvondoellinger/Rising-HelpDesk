package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.jpaRepositories;

import io.github.jvondoellinger.rising_helpdesk.profile.infrastructure.AccessProfileDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAccessProfileRepository extends JpaRepository<AccessProfileDbEntity, String> {
	boolean existsByName(String name);
}
