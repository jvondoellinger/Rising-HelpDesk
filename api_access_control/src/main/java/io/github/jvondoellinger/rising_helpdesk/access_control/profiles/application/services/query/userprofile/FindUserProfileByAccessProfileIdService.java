package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.userprofile.FindUserProfileByAccessProfileIdHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.UserProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.userprofile.FindUserProfileByAccessProfileIdQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindUserProfileByAccessProfileIdService implements FindUserProfileByAccessProfileIdHandler {
	private final UserProfileRepository repository;
	private final UserProfileMapper mapper;

	@Override
	public ResultV1<Pagination<UserProfileDetails>, String> handle(FindUserProfileByAccessProfileIdQuery query) {
		var filter = PaginationFilter.of(query.page(), query.size());
		var pagination = repository.findByAccessProfileId(query.accessProfileId(), filter);

		if (pagination.isEmpty()) {
			return ResultV1.failure("No profile found.");
		}

		var detailsPagination = mapper.detailsPagination(pagination);

		return ResultV1.success(detailsPagination);
	}

	@Override
	public Class<FindUserProfileByAccessProfileIdQuery> getQueryType() {
		return FindUserProfileByAccessProfileIdQuery.class;
	}
}
