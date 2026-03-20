package io.github.jvondoellinger.rising_helpdesk.profile.application.services.query.userprofile;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query.userprofile.FindUserProfileByUserIdHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.UserProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.userprofile.FindUserProfileByUserIdQuery;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.UserProfileRepository;
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
			return new Result.Success<>(null);
		}
		var userprofile = optional.get();
		var details = mapper.details(userprofile);

		return new Result.Success<>(details);
	}

	@Override
	public Class<FindUserProfileByUserIdQuery> getQueryType() {
		return FindUserProfileByUserIdQuery.class;
	}
}
