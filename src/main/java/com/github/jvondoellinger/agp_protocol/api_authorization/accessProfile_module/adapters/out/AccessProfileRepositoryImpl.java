package com.github.jvondoellinger.agp_protocol.api_authorization.accessProfile_module.adapters.out;

import com.github.jvondoellinger.agp_protocol.api_authorization.accessProfile_module.infrastructure.AccessProfileDbEntity;
import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.JpaCrudsBridge;
import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.api_authorization.accessProfile_module.domain.AccessProfile;
import com.github.jvondoellinger.agp_protocol.api_authorization.accessProfile_module.domain.AccessProfileRepository;
import com.github.jvondoellinger.agp_protocol.shared_kernel.QueryFilter;
import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.DbEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class AccessProfileRepositoryImpl implements AccessProfileRepository {
	private final JpaAccessProfileRepository jpaAccessProfileRepository;

	@Override
	public AccessProfile save(AccessProfile entity) {
		return JpaCrudsBridge.save(jpaAccessProfileRepository, new AccessProfileDbEntity(entity), DbEntity::toDomainEntity);
	}

	@Override
	public AccessProfile update(AccessProfile entity) {
		return JpaCrudsBridge.save(jpaAccessProfileRepository, new AccessProfileDbEntity(entity), DbEntity::toDomainEntity);
	}

	@Override
	public void delete(AccessProfile entity) {
		JpaCrudsBridge.delete(jpaAccessProfileRepository, new AccessProfileDbEntity(entity));
	}

	@Override
	public AccessProfile queryById(DomainId id) {
		return JpaCrudsBridge.findById(jpaAccessProfileRepository, id.value(), DbEntity::toDomainEntity);

	}

	@Override
	public List<AccessProfile> query(QueryFilter filter) {
		return JpaCrudsBridge.findBy(jpaAccessProfileRepository, filter, DbEntity::toDomainEntity);

	}
}
