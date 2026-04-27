package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.ResultTransformerStep;

public interface QueryHandler<Q extends Query<R>, R> {
	ResultTransformerStep<R> handle(Q query);

	Class<Q> getQueryType();
}
