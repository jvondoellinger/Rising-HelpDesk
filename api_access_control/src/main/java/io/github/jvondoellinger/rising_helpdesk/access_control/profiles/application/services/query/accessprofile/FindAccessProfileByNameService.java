package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.accessprofile.FindAccessProfileByNameQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.accessprofile.FindAccessProfileByNameQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindAccessProfileByNameService implements FindAccessProfileByNameQueryHandler {
	private final AccessProfileRepository repository;
	private final AccessProfileMapper mapper;

	@Override
	public ResultV1<AccessProfileDetails, String> handle(FindAccessProfileByNameQuery query) {
		var optional = repository.findByName(query.name());

		if (optional.isEmpty()) {
			return ResultV1.failure("No Access Profile found.");
		}

		return ResultV1.success(mapper.details(optional.get()));
	}

	@Override
	public Class<FindAccessProfileByNameQuery> getQueryType() {
		return FindAccessProfileByNameQuery.class;
	}
}
