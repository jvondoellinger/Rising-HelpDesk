package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.out.repository;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.out.jpaRepositories.JpaAccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.out.mappers.AccessProfileDbMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.kernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class AccessProfileRepositoryImpl implements AccessProfileRepository {
	private final JpaAccessProfileRepository jpaAccessProfileRepository;
	private final AccessProfileDbMapper mapper;

	@Override
	public void save(AccessProfile entity) {

	}

	@Override
	public void update(AccessProfile entity) {
	}

	@Override
	public void delete(AccessProfile entity) {
	}

	@Override
	public Optional<AccessProfile> findById(UUID id) {
		var optional = jpaAccessProfileRepository.findById(id);

		if (optional.isEmpty()) {
			return Optional.empty();
		}

		var accessProfile = optional.get();

		return Optional.of(mapper.toAccessProfile(accessProfile));
	}

	@Override
	public boolean existsById(UUID accessProfileId) {
		return jpaAccessProfileRepository.existsById(accessProfileId);
	}

	@Override
	public Pagination<AccessProfile> findByPagination(PaginationFilter filter) {
		var pageable = PageRequest.of(filter.page(), filter.size());
		var page = jpaAccessProfileRepository.findAll(pageable);
		var items = page.stream()
			   .map(mapper::toAccessProfile)
			   .toList();
		return Pagination.of(items, page.getNumber(), page.getTotalPages());
	}

	@Override
	public long total() {
		return jpaAccessProfileRepository.count();
	}

	@Override
	public boolean existsByName(String name) {
		return jpaAccessProfileRepository.existsByName(name);
	}

	@Override
	public Optional<AccessProfile> findByName(String name) {
		return jpaAccessProfileRepository.findByName(name)
			   .map(mapper::toAccessProfile);
	}
}
