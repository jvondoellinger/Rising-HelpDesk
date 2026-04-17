package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.accessprofile.FindAccessProfileByIdQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.accessprofile.FindAccessProfileByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindAccessProfileByIdQueryService implements FindAccessProfileByIdQueryHandler {
	private final AccessProfileRepository repository;
	private final AccessProfileMapper mapper;

	@Override
	public Result<AccessProfileDetails> handle(FindAccessProfileByIdQuery query) {
		var optional = repository.findById(query.id());

		if (optional.isEmpty()) {
			return Result.failure("No Access Profile found.");
		}

		var accessprofile = optional.get();
		var mapped = mapper.details(accessprofile);
		return Result.success(mapped);
	}

	@Override
	public Class<FindAccessProfileByIdQuery> getQueryType() {
		return FindAccessProfileByIdQuery.class;
	}
}
