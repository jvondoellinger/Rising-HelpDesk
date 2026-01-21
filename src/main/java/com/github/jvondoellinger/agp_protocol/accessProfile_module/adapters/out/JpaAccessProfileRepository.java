package com.github.jvondoellinger.agp_protocol.accessProfile_module.adapters.out;

import com.github.jvondoellinger.agp_protocol.accessProfile_module.infrastructure.AccessProfileDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAccessProfileRepository extends JpaRepository<AccessProfileDbEntity, String> {
}
