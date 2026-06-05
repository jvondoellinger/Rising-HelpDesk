package io.github.jvondoellinger.risinghelpdesk.shared.cqrs;

import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;

public interface QueryHandler<Q extends Query<R>, R> {
	ResultB<R> handle(Q query);

	Class<Q> getQueryType();
}
