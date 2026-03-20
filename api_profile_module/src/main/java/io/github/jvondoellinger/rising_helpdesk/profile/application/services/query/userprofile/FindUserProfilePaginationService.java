package io.github.jvondoellinger.rising_helpdesk.profile.application.services.query.userprofile;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query.userprofile.FindUserProfilePaginationHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.UserProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.userprofile.FindUserProfilePaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindUserProfilePaginationService implements FindUserProfilePaginationHandler {
	private final UserProfileRepository repository;
	private final UserProfileMapper mapper;

	@Override
	public Result<Pagination<UserProfileDetails>> handle(FindUserProfilePaginationQuery query) {
		var pagination = repository.findByPagination(PaginationFilter.of(query.size(), query.page()));
		var paginationMapped = mapper.detailsPagination(pagination);

		return new Result.Success<>(paginationMapped);
	}

	@Override
	public Class<FindUserProfilePaginationQuery> getQueryType() {
		return FindUserProfilePaginationQuery.class;
	}
}
