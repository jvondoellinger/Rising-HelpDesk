package com.github.jvondoellinger.agp_protocol.api_profile.adapters.out;

import com.github.jvondoellinger.agp_protocol.api_profile.adapters.out.mappers.AccessProfileDbMapper;
import com.github.jvondoellinger.agp_protocol.api_profile.domain.AccessProfile;
import com.github.jvondoellinger.agp_protocol.api_profile.domain.AccessProfileRepository;
import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.QueryFilter;
import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.JpaCrudsBridge;
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
