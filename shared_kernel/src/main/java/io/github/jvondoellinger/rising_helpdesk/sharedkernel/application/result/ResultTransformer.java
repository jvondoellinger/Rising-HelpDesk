package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result;

public interface ResultTransformer<I> {
	static <T> ResultTransformer<T> from(T value) {
		return ResultTransformerImpl.create(value);
	}

	// <O> ResultTransformer<O> map(ResultFunc<I, O> supplier);

	<O> ResultTransformer<O> flatMap(ResultFunc<I, Result<O>> supplier);
	ResultTransformer<I> switchIfEmpty(ResultFunc<I, Result<I>> supplier);
	ResultTransformer<I> switchIfEmpty(I value);

	Result<I> then();
}
