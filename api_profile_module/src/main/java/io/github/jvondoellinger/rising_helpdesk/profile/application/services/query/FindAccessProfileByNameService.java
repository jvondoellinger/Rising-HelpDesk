package io.github.jvondoellinger.rising_helpdesk.profile.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query.FindAccessProfileByNameQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.FindAccessProfileByNameQuery;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class FindAccessProfileByNameService implements FindAccessProfileByNameQueryHandler {
	private final AccessProfileRepository repository;

	@Override
	public Result<AccessProfileDetails> handle(FindAccessProfileByNameQuery query) {
		return null;
	}

	@Override
	public Class<FindAccessProfileByNameQuery> getQueryType() {
		return FindAccessProfileByNameQuery.class;
	}
}
