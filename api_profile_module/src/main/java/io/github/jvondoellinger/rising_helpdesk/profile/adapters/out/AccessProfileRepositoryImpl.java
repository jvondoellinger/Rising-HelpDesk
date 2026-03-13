package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.mappers.AccessProfileDbMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.profile.infrastructure.AccessProfileDbEntity;
import io.github.jvondoellinger.rising_helpdesk.profile.infrastructure.UserProfileDbEntity;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;

@Repository
@AllArgsConstructor
public class AccessProfileRepositoryImpl implements AccessProfileRepository {
	private final JpaAccessProfileRepository jpaAccessProfileRepository;
	private final AccessProfileDbMapper mapper;

	@Override
	public AccessProfile save(AccessProfile entity) {
		return JpaCrudsBridge.save(
			   jpaAccessProfileRepository,
			   mapper.from(entity),
			   mapper::toAccessProfile);
	}

	@Override
	public AccessProfile update(AccessProfile entity) {
		return JpaCrudsBridge.save(jpaAccessProfileRepository, mapper.from(entity), mapper::toAccessProfile);
	}

	@Override
	public void delete(AccessProfile entity) {
		JpaCrudsBridge.delete(jpaAccessProfileRepository, mapper.from(entity));
	}

	@Override
	public AccessProfile queryById(AccessProfileId id) {
		return JpaCrudsBridge.findById(jpaAccessProfileRepository, id.toString(), mapper::toAccessProfile);
	}

	@Override
	public boolean existsById(AccessProfileId accessProfileId) {
		return jpaAccessProfileRepository.existsById(accessProfileId.toString());
	}

	@Override
	public Pagination<AccessProfile> query(QueryFilter filter) {
		return paginationFunc(filter, jpaAccessProfileRepository::findAll);

	}

	@Override
	public long total() {
		return jpaAccessProfileRepository.count();
	}

	@Override
	public boolean existsByName(String name) {
		return jpaAccessProfileRepository.existsByName(name);
	}

	private Pagination<AccessProfile> paginationFunc(QueryFilter filter, Function<PageRequest, Page<AccessProfileDbEntity>> function) {
		var page = function.apply(PageRequest.of(filter.page(), filter.size()));
		var accessprofile = page.get()
				.map(mapper::toAccessProfile)
				.toList();

		return new Pagination<>(accessprofile, page.getNumber(), page.getSize(), page.getTotalPages());
	}
}
