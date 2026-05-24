package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.permissions;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.permission.FindPermissionPaginationHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.permission.FindPermissionPaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.PermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.kernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStepImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindPermissionPaginationService implements FindPermissionPaginationHandler {
	private final PermissionRepository repository;
	private final PermissionMapper mapper;

	@Override
	public ResultB<Pagination<PermissionDetails>> handle(FindPermissionPaginationQuery query) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var pagination = repository.findByPagination(PaginationFilter.of(query.page(), query.size()));
				   var paginationMapped = mapper.details(pagination);

				   return ResultB.create().map(v -> paginationMapped);
			   });
	}

	@Override
	public Class<FindPermissionPaginationQuery> getQueryType() {
		return FindPermissionPaginationQuery.class;
	}
}
