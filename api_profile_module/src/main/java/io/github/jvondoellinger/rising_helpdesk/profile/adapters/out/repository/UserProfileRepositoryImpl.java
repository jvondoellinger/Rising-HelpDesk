package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.repository;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.repository.helper.JpaCrudsBridge;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.jpaRepositories.JpaUserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.mappers.UserProfileDbMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.entities.UserProfileDbEntity;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.function.Function;


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
	public UserProfile queryById(UUID id) {
		return JpaCrudsBridge.findById(jpaUserProfileRepository, id.toString(), mapper::toUserProfile);
	}

	@Override
	public boolean existsById(UUID id) {
		return jpaUserProfileRepository.existsById(id.toString());
	}

	@Override
	public Pagination<UserProfile> query(QueryFilter filter) {
		return paginationFunc(filter, jpaUserProfileRepository::findAll);
	}

	@Override
	public long total() {
		return jpaUserProfileRepository.count();
	}

	private Pagination<UserProfile> paginationFunc(QueryFilter filter, Function<PageRequest, Page<UserProfileDbEntity>> function) {
		var page = function.apply(PageRequest.of(filter.page(), filter.size()));
		var userprofiles = page.get()
				.map(mapper::toUserProfile)
				.toList();

		return new Pagination<>(userprofiles, page.getNumber(), page.getSize(), page.getTotalPages());
	}
}
