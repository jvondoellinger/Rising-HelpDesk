package io.github.jvondoellinger.rising_helpdesk.kernel.application.result;

public interface ResultTransformerStep<I> {
	static <T> ResultTransformerStep<T> from(T value) {
		return ResultTransformerStepImpl.create(value);
	}
	static ResultTransformerStep<Void> create() {
		return ResultTransformerStepImpl.create(null);
	}

	// <O> ResultTransformerStep<O> map(ResultFunc<I, O> supplier);

	<O> ResultTransformerStep<O> flatMap(ResultFunc<I, ResultA<O>> supplier);
	ResultTransformerStep<I> switchIfEmpty(ResultFunc<I, ResultA<I>> supplier);
	ResultTransformerStep<I> switchIfEmpty(I value);

	ResultA<I> then();
}
