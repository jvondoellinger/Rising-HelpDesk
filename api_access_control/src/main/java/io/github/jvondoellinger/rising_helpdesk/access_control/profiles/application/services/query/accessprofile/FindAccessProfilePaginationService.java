package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.accessprofile.FindAccessProfilePaginationHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.accessprofile.FindAccessProfilePaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.kernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service
@AllArgsConstructor
public final class FindAccessProfilePaginationService implements FindAccessProfilePaginationHandler {
	private final AccessProfileRepository repository;
	private final AccessProfileMapper mapper;

	@Override
	public ResultTransformerStep<Pagination<AccessProfileDetails>> handle(FindAccessProfilePaginationQuery query) {
		return ResultTransformerStep.create()
			   .flatMap(aVoid -> {
				   if (query.size() < 0 || query.page() < 0) {
					   return Result.error(new DomainError("SIZE_OR_PAGE_NUMBER_CANNOT_BE_SMALLER_0", "Size or page number cannot be smaller 0."));
				   }

				   var pagination = repository.findByPagination(PaginationFilter.of(query.page(), query.size()));
				   var paginationMapped = mapper.detailsPagination(pagination);

				   return Result.success(paginationMapped);
			   });
	}

	@Override
	public Class<FindAccessProfilePaginationQuery> getQueryType() {
		return FindAccessProfilePaginationQuery.class;
	}
}
