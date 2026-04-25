package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;

public interface QueryHandler<Q extends Query<R>, R> {
	Result<R> handle(Q query);

	Class<Q> getQueryType();
}
