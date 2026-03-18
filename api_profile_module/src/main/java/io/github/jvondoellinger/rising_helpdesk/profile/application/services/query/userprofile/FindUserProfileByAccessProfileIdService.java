package io.github.jvondoellinger.rising_helpdesk.profile.application.services.query.userprofile;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query.userprofile.FindUserProfileByAccessProfileIdHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.UserProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.userprofile.FindUserProfileByAccessProfileIdQuery;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindUserProfileByAccessProfileIdService implements FindUserProfileByAccessProfileIdHandler {
	private final UserProfileRepository repository;
	private final UserProfileMapper mapper;

	@Override
	public Result<Pagination<UserProfileDetails>> handle(FindUserProfileByAccessProfileIdQuery query) {
		var pagination = repository.findByAccessProfileId(query.accessProfileId(), PaginationFilter.of(query.size(), query.page()))
			   .orElse(null);

		if (pagination == null) {
			return new Result.Failure<>(new KernelException("No profile found."));
		}

		var paginationMapped = mapper.detailsPagination(pagination);

		return new Result.Success<>(paginationMapped);
	}

	@Override
	public Class<FindUserProfileByAccessProfileIdQuery> getQueryType() {
		return FindUserProfileByAccessProfileIdQuery.class;
	}
}
