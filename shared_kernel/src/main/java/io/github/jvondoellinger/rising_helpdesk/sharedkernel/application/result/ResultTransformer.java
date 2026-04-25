package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.abstraction;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.impl.ResultTransformerImpl;

public interface ResultTransformer<I> {
	static <T> ResultTransformer<T> from(T value) {
		return ResultTransformerImpl.create(value);
	}

	// <O> ResultTransformer<O> map(ResultFunc<I, O> supplier);
	// <O> ResultTransformer<O> flatMap(ResultFunc<I, ErrorResult<O>> supplier);
	<O> ResultTransformer<O> flatMap(ResultFunc<I, Result<O>> supplier);
	<O> ResultTransformer<O> switchIfEmpty(ResultFunc<I, Result<O>> supplier);
	<O> ResultTransformer<O> switchIfEmpty(O value);

	Result<I> then();
}
