package io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;

public interface QueryHandler<Q extends Query<R>, R> {
	ResultB<R> handle(Q query);

	Class<Q> getQueryType();
}
