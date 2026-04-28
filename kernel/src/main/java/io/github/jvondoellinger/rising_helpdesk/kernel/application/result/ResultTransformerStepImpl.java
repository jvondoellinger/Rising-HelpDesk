package io.github.jvondoellinger.rising_helpdesk.kernel.application.result;

public class ResultTransformerStepImpl<T> implements ResultTransformerStep<T> {
	private final T value;
	private final DomainError error;

	private ResultTransformerStepImpl(T value) {
		this.value = value;
		this.error = null;
	}

	private ResultTransformerStepImpl(T value, DomainError error) {
		this.value = value;
		this.error = error;
	}

	@Override
	public <O> ResultTransformerStep<O> flatMap(ResultFunc<T, Result<O>> func) {
		if (error != null) {
			return createError(error);
		}
		try {
			var result = func.run(value);

			if (result.isError())
				return createError(result.getError());

			return create(result.getValue());
		} catch (RuntimeException e) {
			var error = new DomainError("Unexpected error", e.getMessage());
			return createError(error);
		}
	}

	@Override
	public ResultTransformerStep<T> switchIfEmpty(ResultFunc<T, Result<T>> supplier) {
		if (value != null) {
			return this;
		}
		return flatMap(supplier);
	}

	@Override
	public ResultTransformerStep<T> switchIfEmpty(T value) {
		if (this.value != null) {
			return this;
		}
		return new ResultTransformerStepImpl<>(value);
	}

	@Override
	public Result<T> then() {
		return new ResultImpl<>(value, error);
	}

	// Statis
	public static <T> ResultTransformerStepImpl<T> create(T value) {
		return new ResultTransformerStepImpl<>(value);
	}
	public static <T> ResultTransformerStepImpl<T> create(T value, DomainError error) {
		return new ResultTransformerStepImpl<>(value, error);
	}

	// Helper
	private static <T> ResultTransformerStepImpl<T> createError(DomainError error) {
		return new ResultTransformerStepImpl<>(null, error);
	}
}
