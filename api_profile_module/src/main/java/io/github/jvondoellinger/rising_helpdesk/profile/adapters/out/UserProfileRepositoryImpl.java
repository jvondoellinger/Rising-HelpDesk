package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.mappers.UserProfileDbMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
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
