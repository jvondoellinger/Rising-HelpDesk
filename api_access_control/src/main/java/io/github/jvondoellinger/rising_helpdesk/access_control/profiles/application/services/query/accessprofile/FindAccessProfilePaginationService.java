package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.accessprofile.FindAccessProfilePaginationHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.accessprofile.FindAccessProfilePaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public final class FindAccessProfilePaginationService implements FindAccessProfilePaginationHandler {
	private final AccessProfileRepository repository;
	private final AccessProfileMapper mapper;

	@Override
	public ResultV1<Pagination<AccessProfileDetails>, String> handle(FindAccessProfilePaginationQuery query) {
		if (query.size() < 0 || query.page() < 0) {
			return ResultV1.failure("Size or page number cannot be smaller 0.");
		}

		var pagination = repository.findByPagination(PaginationFilter.of(query.page(), query.size()));
		var paginationMapped = mapper.detailsPagination(pagination);

		return ResultV1.success(paginationMapped);
	}

	@Override
	public Class<FindAccessProfilePaginationQuery> getQueryType() {
		return FindAccessProfilePaginationQuery.class;
	}
}
