package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.permissions;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.permission.FindPermissionByCodeHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.permission.FindPermissionByCodeQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.PermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.shared.application.result.DomainError;

@Service
@AllArgsConstructor
public class FindPermissionByCodeService implements FindPermissionByCodeHandler {
	private final PermissionRepository repository;
	private final PermissionMapper mapper;


	@Override
	public ResultB<PermissionDetails> handle(FindPermissionByCodeQuery query) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var persistence = repository.findByCode(query.code()).orElse(null);

				   if (persistence == null)
					   return ResultB.error(new DomainError("NO_CODE_FOUND", "No code found."));

				   var details = mapper.details(persistence);

				   return ResultB.of(details);
			   });
	}

	@Override
	public Class<FindPermissionByCodeQuery> getQueryType() {
		return FindPermissionByCodeQuery.class;
	}
}
