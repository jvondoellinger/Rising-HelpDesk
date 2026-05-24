package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.userprofile.FindUserProfileByAccessProfileIdHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.UserProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.userprofile.FindUserProfileByAccessProfileIdQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.kernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStepImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class FindUserProfileByAccessProfileIdService implements FindUserProfileByAccessProfileIdHandler {
	private final UserProfileRepository repository;
	private final UserProfileMapper mapper;

	@Override
	public ResultB<Pagination<UserProfileDetails>> handle(FindUserProfileByAccessProfileIdQuery query) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var filter = PaginationFilter.of(query.page(), query.size());
				   var pagination = repository.findByAccessProfileId(query.accessProfileId(), filter);

				   if (pagination.isEmpty()) {
					   return ResultB.error(new DomainError("NO_PROFILE_FOUND", "No profile found."));
				   }

				   var detailsPagination = mapper.detailsPagination(pagination);

				   return ResultB.of(detailsPagination);
			   });
	}

	@Override
	public Class<FindUserProfileByAccessProfileIdQuery> getQueryType() {
		return FindUserProfileByAccessProfileIdQuery.class;
	}
}
