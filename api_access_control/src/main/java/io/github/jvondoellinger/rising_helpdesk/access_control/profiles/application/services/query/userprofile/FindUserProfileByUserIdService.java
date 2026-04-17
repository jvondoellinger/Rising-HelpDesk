package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.userprofile.FindUserProfileByUserIdHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.UserProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.userprofile.FindUserProfileByUserIdQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.UserProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindUserProfileByUserIdService implements FindUserProfileByUserIdHandler {
	private final UserProfileRepository repository;
	private final UserProfileMapper mapper;

	@Override
	public Result<UserProfileDetails> handle(FindUserProfileByUserIdQuery query) {
		var optional = repository.findById(query.userId());

		if (optional.isEmpty()) {
			return Result.failure("No User Profile found.");
		}
		var userprofile = optional.get();
		var details = mapper.details(userprofile);

		return Result.success(details);
	}

	@Override
	public Class<FindUserProfileByUserIdQuery> getQueryType() {
		return FindUserProfileByUserIdQuery.class;
	}
}
