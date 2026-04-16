package io.github.jvondoellinger.rising_helpdesk.access_control.application.services.query.permissions;

import io.github.jvondoellinger.rising_helpdesk.access_control.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.handlers.query.permission.FindPermissionByCodeHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.queries.permission.FindPermissionByCodeQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.domain.repository.PermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindPermissionByCodeService implements FindPermissionByCodeHandler {
	private final PermissionRepository repository;
	private final PermissionMapper mapper;


	@Override
	public Result<PermissionDetails> handle(FindPermissionByCodeQuery query) {
		var persistence = repository.findByCode(query.code()).orElse(null);

		if (persistence == null) {
			return Result.failure("No code found.");
		}

		var details = mapper.details(persistence);

		return Result.success(details);
	}

	@Override
	public Class<FindPermissionByCodeQuery> getQueryType() {
		return FindPermissionByCodeQuery.class;
	}
}
