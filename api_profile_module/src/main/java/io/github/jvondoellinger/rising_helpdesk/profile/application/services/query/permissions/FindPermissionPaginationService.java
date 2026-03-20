package io.github.jvondoellinger.rising_helpdesk.profile.application.services.query.permissions;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query.permission.FindPermissionPaginationHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.PermissionMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.permission.FindPermissionPaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.PermissionRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindPermissionPaginationService implements FindPermissionPaginationHandler {
	private final PermissionRepository repository;
	private final PermissionMapper mapper;

	@Override
	public Result<Pagination<PermissionDetails>> handle(FindPermissionPaginationQuery query) {
		var pagination = repository.findByPagination(PaginationFilter.of(query.size(),query.page()));

		var paginationMapped = mapper.details(pagination);
		return new Result.Success<>(paginationMapped);
	}

	@Override
	public Class<FindPermissionPaginationQuery> getQueryType() {
		return FindPermissionPaginationQuery.class;
	}
}
