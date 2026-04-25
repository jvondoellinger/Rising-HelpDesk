package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result;

public class ResultTransformerImpl<T> implements ResultTransformer<T> {
	private final T value;
	private final DomainError error;

	private ResultTransformerImpl(T value) {
		this.value = value;
		this.error = null;
	}

	private ResultTransformerImpl(T value, DomainError error) {
		this.value = value;
		this.error = error;
	}

	@Override
	public <O> ResultTransformer<O> flatMap(ResultFunc<T, Result<O>> func) {
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
	public ResultTransformer<T> switchIfEmpty(ResultFunc<T, Result<T>> supplier) {
		if (value != null) {
			return this;
		}
		return flatMap(supplier);
	}

	@Override
	public ResultTransformer<T> switchIfEmpty(T value) {
		if (this.value != null) {
			return this;
		}
		return new ResultTransformerImpl<>(value);
	}

	@Override
	public Result<T> then() {
		return new ResultImpl<>(value, error);
	}

	// Statis
	public static <T> ResultTransformerImpl<T> create(T value) {
		return new ResultTransformerImpl<>(value);
	}
	public static <T> ResultTransformerImpl<T> create(T value, DomainError error) {
		return new ResultTransformerImpl<>(value, error);
	}

	// Helper
	private static <T> ResultTransformerImpl<T> createError(DomainError error) {
		return new ResultTransformerImpl<>(null, error);
	}
}
