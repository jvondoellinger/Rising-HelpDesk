package io.github.jvondoellinger.rising_helpdesk.adapters.out;

import io.github.jvondoellinger.rising_helpdesk.infrastructure.AccessProfileDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAccessProfileRepository extends JpaRepository<AccessProfileDbEntity, String> {
}
