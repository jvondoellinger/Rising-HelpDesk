package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application;

public interface QueryHandler<Q extends Query<R>, R> {
	Result<R, String> handle(Q query);

	Class<Q> getQueryType();
}
