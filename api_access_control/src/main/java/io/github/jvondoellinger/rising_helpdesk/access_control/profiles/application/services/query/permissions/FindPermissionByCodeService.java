package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.permissions;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.permission.FindPermissionByCodeHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.permission.FindPermissionByCodeQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.PermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class FindPermissionByCodeService implements FindPermissionByCodeHandler {
	private final PermissionRepository repository;
	private final PermissionMapper mapper;


	@Override
	public Result<PermissionDetails> handle(FindPermissionByCodeQuery query) {
		var persistence = repository.findByCode(query.code()).orElse(null);

		if (persistence == null) {
			return Result.error(new DomainError("NO_CODE_FOUND", "No code found."));
		}

		var details = mapper.details(persistence);

		return Result.success(details);
	}

	@Override
	public Class<FindPermissionByCodeQuery> getQueryType() {
		return FindPermissionByCodeQuery.class;
	}
}
