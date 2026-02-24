package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.mappers.AccessProfileDbMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
		return JpaCrudsBridge.findById(jpaAccessProfileRepository, id.value(), mapper::toAccessProfile);

	}

	@Override
	public List<AccessProfile> query(QueryFilter filter) {
		return JpaCrudsBridge.findBy(jpaAccessProfileRepository, filter, mapper::toAccessProfile);

	}

	@Override
	public long total() {
		return jpaAccessProfileRepository.count();
	}

	@Override
	public boolean existsByName(String name) {
		return jpaAccessProfileRepository.existsByName(name);
	}
}
