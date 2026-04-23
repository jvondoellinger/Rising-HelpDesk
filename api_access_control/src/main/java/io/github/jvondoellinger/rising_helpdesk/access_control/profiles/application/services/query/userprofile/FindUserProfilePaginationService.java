package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.userprofile.FindUserProfilePaginationHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.UserProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.userprofile.FindUserProfilePaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.UserProfileRepository;
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
	public Result<Pagination<UserProfileDetails>, String> handle(FindUserProfilePaginationQuery query) {
		var pagination = repository.findByPagination(PaginationFilter.of(query.page(), query.size()));
		var paginationMapped = mapper.detailsPagination(pagination);

		return Result.success(paginationMapped);
	}

	@Override
	public Class<FindUserProfilePaginationQuery> getQueryType() {
		return FindUserProfilePaginationQuery.class;
	}
}
