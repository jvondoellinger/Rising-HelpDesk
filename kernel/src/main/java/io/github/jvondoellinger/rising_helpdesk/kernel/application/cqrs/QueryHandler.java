package io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;

public interface QueryHandler<Q extends Query<R>, R> {
	ResultTransformerStep<R> handle(Q query);

	Class<Q> getQueryType();
}
