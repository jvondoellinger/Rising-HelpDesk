package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.services.query.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.query.accessprofile.FindAccessProfileByIdQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.accessprofile.FindAccessProfileByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.shared.application.result.DomainError;

@Service
@AllArgsConstructor
public class FindAccessProfileByIdQueryService implements FindAccessProfileByIdQueryHandler {
	private final AccessProfileRepository repository;
	private final AccessProfileMapper mapper;

	@Override
	public ResultB<AccessProfileDetails> handle(FindAccessProfileByIdQuery query) {
		return ResultB.create()
			   .flatMap(() -> {
				   var optional = repository.findById(query.id());

				   if (optional.isEmpty()) {
					   return ResultB.error(new DomainError("NO_ACCESS_PROFILE_FOUND", "No Access Profile found."));
				   }

				   var accessprofile = optional.get();
				   var mapped = mapper.details(accessprofile);

				   return ResultB.of(mapped);
			   });
	}

	@Override
	public Class<FindAccessProfileByIdQuery> getQueryType() {
		return FindAccessProfileByIdQuery.class;
	}
}
