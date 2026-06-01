package io.github.jvondoellinger.rising_helpdesk.shared.application.cqrs;

import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;

public interface QueryHandler<Q extends Query<R>, R> {
	ResultB<R> handle(Q query);

	Class<Q> getQueryType();
}
