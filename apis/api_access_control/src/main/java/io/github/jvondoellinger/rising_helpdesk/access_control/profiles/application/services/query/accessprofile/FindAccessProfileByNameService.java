package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.accessprofile.FindAccessProfileByNameQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.accessprofile.FindAccessProfileByNameQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStepImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class FindAccessProfileByNameService implements FindAccessProfileByNameQueryHandler {
	private final AccessProfileRepository repository;
	private final AccessProfileMapper mapper;

	@Override
	public ResultB<AccessProfileDetails> handle(FindAccessProfileByNameQuery query) {
		return ResultB.create()
			   .<AccessProfileDetails>flatMap(avoid -> {
				   var optional = repository.findByName(query.name());
				   if (optional.isEmpty())
					   return ResultB.error(new DomainError("NO_ACCESS_PROFILE_FOUND", "No Access Profile found."));

				   var details = mapper.details(optional.get());
				   return ResultB.of(details);
			   });
	}

	@Override
	public Class<FindAccessProfileByNameQuery> getQueryType() {
		return FindAccessProfileByNameQuery.class;
	}
}
