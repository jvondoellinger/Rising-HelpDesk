package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.permissions;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.permission.FindPermissionPaginationHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.permission.FindPermissionPaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.PermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindPermissionPaginationService implements FindPermissionPaginationHandler {
	private final PermissionRepository repository;
	private final PermissionMapper mapper;

	@Override
	public ResultV1<Pagination<PermissionDetails>, String> handle(FindPermissionPaginationQuery query) {
		var pagination = repository.findByPagination(PaginationFilter.of(query.page(), query.size()));

		var paginationMapped = mapper.details(pagination);
		return ResultV1.success(paginationMapped);
	}

	@Override
	public Class<FindPermissionPaginationQuery> getQueryType() {
		return FindPermissionPaginationQuery.class;
	}
}
