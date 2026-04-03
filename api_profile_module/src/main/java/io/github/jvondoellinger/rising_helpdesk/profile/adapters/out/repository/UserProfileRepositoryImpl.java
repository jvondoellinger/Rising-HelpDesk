package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.repository;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.jpaRepositories.JpaUserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.mappers.UserProfileDbMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
@AllArgsConstructor
public class UserProfileRepositoryImpl implements UserProfileRepository {
	private final JpaUserProfileRepository jpaUserProfileRepository;
	private final UserProfileDbMapper mapper;

	@Override
	public void save(UserProfile entity) {
		var dbEntity = mapper.from(entity);

		jpaUserProfileRepository.save(dbEntity);
	}

	@Override
	public void update(UserProfile entity) {
		var dbEntity = mapper.from(entity);

		jpaUserProfileRepository.save(dbEntity);
	}

	@Override
	public void delete(UserProfile entity) {
		if (entity.getUserId() == null) {
			throw new RuntimeException("User profile can't have a null userId");
		}

		jpaUserProfileRepository.deleteById(entity.getUserId());
	}

	@Override
	public Optional<UserProfile> findById(UUID id) {
		var optional = jpaUserProfileRepository.findById(id);

		if (optional.isEmpty()) {
			return Optional.empty();
		}
		var userProfile = optional.get();

		return Optional.of(mapper.toUserProfile(userProfile));
	}

	@Override
	public boolean existsById(UUID id) {
		return jpaUserProfileRepository.existsById(id);
	}

	@Override
	public Pagination<UserProfile> findByPagination(PaginationFilter filter) {
		var pageable = PageRequest.of(filter.page(), filter.size());
		var page = jpaUserProfileRepository.findAll(pageable);
		var items = page
			.stream()
			.map(mapper::toUserProfile)
			.toList();
		return Pagination.of(items, page.getNumber(), page.getTotalPages());
	}

	@Override
	public long total() {
		return jpaUserProfileRepository.count();
	}

	@Override
	public Pagination<UserProfile> findByAccessProfileId(UUID accessProfileId, PaginationFilter filter) {
		var pageable = PageRequest.of(filter.page(), filter.size());
		var page = jpaUserProfileRepository.findByAccessProfileId(accessProfileId, pageable);
		var items = page.stream()
			   .map(mapper::toUserProfile)
			   .toList();
		return Pagination.of(items, page.getNumber(), page.getTotalPages());

	}
}
