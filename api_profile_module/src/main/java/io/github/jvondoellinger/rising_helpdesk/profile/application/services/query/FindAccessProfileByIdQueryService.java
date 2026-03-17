package io.github.jvondoellinger.rising_helpdesk.profile.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query.FindAccessProfileByIdQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.mappers.AccessProfileMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.FindAccessProfileByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.repository.AccessProfileRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
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
		var current = repository.queryById(query.id());
		if (current == null) {
			return new Result.Failure<>(new KernelException("ID not found."));
		}

		var mapped = mapper.details(current);
		return new Result.Success<>(mapped);
	}

	@Override
	public Class<FindAccessProfileByIdQuery> getQueryType() {
		return FindAccessProfileByIdQuery.class;
	}
}
