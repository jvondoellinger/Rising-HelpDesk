package com.github.jvondoellinger.agp_protocol.api_authorization.userProfile_module.adapters.out;

import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.JpaCrudsBridge;
import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.api_authorization.userProfile_module.domain.UserProfile;
import com.github.jvondoellinger.agp_protocol.api_authorization.userProfile_module.domain.UserProfileRepository;
import com.github.jvondoellinger.agp_protocol.shared_kernel.QueryFilter;
import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.DbEntity;
import com.github.jvondoellinger.agp_protocol.api_authorization.userProfile_module.infrastructure.UserProfileDbEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserProfileRepositoryImpl implements UserProfileRepository {
	private final JpaUserProfileRepository jpaUserProfileRepository;

	@Override
	public UserProfile save(UserProfile entity) {
		return JpaCrudsBridge.save(jpaUserProfileRepository, new UserProfileDbEntity(entity), DbEntity::toDomainEntity);
	}

	@Override
	public UserProfile update(UserProfile entity) {
		return JpaCrudsBridge.save(jpaUserProfileRepository, new UserProfileDbEntity(entity), DbEntity::toDomainEntity);
	}

	@Override
	public void delete(UserProfile entity) {
		JpaCrudsBridge.delete(jpaUserProfileRepository, new UserProfileDbEntity(entity));
	}

	@Override
	public UserProfile queryById(DomainId id) {
		return JpaCrudsBridge.findById(jpaUserProfileRepository, id.value(), DbEntity::toDomainEntity);
	}

	@Override
	public List<UserProfile> query(QueryFilter filter) {
		return JpaCrudsBridge.findBy(jpaUserProfileRepository, filter, DbEntity::toDomainEntity);
	}
}
