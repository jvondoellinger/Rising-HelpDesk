package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.userprofile.FindUserProfilePaginationHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.UserProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.userprofile.FindUserProfilePaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.kernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindUserProfilePaginationService implements FindUserProfilePaginationHandler {
	private final UserProfileRepository repository;
	private final UserProfileMapper mapper;

	@Override
	public ResultTransformerStep<Pagination<UserProfileDetails>> handle(FindUserProfilePaginationQuery query) {
		return ResultTransformerStep.create()
			   .flatMap(aVoid -> {
				   var pagination = repository.findByPagination(PaginationFilter.of(query.page(), query.size()));
				   var paginationMapped = mapper.detailsPagination(pagination);

				   return Result.success(paginationMapped);
			   });
	}

	@Override
	public Class<FindUserProfilePaginationQuery> getQueryType() {
		return FindUserProfilePaginationQuery.class;
	}
}
