package com.github.jvondoellinger.agp_protocol.api_profile.adapters.out;

import com.github.jvondoellinger.agp_protocol.api_profile.adapters.out.mappers.UserProfileDbMapper;
import com.github.jvondoellinger.agp_protocol.api_profile.domain.UserProfile;
import com.github.jvondoellinger.agp_protocol.api_profile.domain.UserProfileRepository;
import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.JpaCrudsBridge;
import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.QueryFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserProfileRepositoryImpl implements UserProfileRepository {
	private final JpaUserProfileRepository jpaUserProfileRepository;
	private final UserProfileDbMapper mapper;

	@Override
	public UserProfile save(UserProfile entity) {
		return JpaCrudsBridge.save(jpaUserProfileRepository, mapper.from(entity), mapper::toUserProfile);
	}

	@Override
	public UserProfile update(UserProfile entity) {
		return JpaCrudsBridge.save(jpaUserProfileRepository, mapper.from(entity), mapper::toUserProfile);
	}

	@Override
	public void delete(UserProfile entity) {
		JpaCrudsBridge.delete(jpaUserProfileRepository, mapper.from(entity));
	}

	@Override
	public UserProfile queryById(DomainId id) {
		return JpaCrudsBridge.findById(jpaUserProfileRepository, id.value(), mapper::toUserProfile);
	}

	@Override
	public List<UserProfile> query(QueryFilter filter) {
		return JpaCrudsBridge.findBy(jpaUserProfileRepository, filter, mapper::toUserProfile);
	}

	@Override
	public long total() {
		return jpaUserProfileRepository.count();
	}
}
