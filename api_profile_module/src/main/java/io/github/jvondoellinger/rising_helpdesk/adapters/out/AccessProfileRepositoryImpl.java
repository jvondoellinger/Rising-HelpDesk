package io.github.jvondoellinger.rising_helpdesk.adapters.out;

import io.github.jvondoellinger.rising_helpdesk.adapters.out.mappers.AccessProfileDbMapper;
import io.github.jvondoellinger.rising_helpdesk.domain.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.domain.AccessProfileRepository;
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
	public AccessProfile queryById(DomainId id) {
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
}
