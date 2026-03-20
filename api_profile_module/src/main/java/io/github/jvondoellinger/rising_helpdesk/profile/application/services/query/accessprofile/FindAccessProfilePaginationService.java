package io.github.jvondoellinger.rising_helpdesk.profile.application.services.query.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query.accessprofile.FindAccessProfilePaginationHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.accessprofile.FindAccessProfilePaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public final class FindAccessProfilePaginationService implements FindAccessProfilePaginationHandler {
	private final AccessProfileRepository repository;
	private final AccessProfileMapper mapper;

	@Override
	public Result<Pagination<AccessProfileDetails>> handle(FindAccessProfilePaginationQuery query) {
		if (query.size() < 0 || query.page() < 0) {
			return new Result.Failure<>(new KernelException("Size or page number cannot be smaller 0."));
		}

		var pagination = repository.findByPagination(PaginationFilter.of(query.size(),query.page()));
		var paginationMapped = mapper.detailsPagination(pagination);

		return new Result.Success<>(paginationMapped);
	}

	@Override
	public Class<FindAccessProfilePaginationQuery> getQueryType() {
		return FindAccessProfilePaginationQuery.class;
	}
}
