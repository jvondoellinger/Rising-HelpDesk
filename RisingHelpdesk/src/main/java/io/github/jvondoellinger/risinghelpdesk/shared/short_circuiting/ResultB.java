package io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting;

import io.github.jvondoellinger.risinghelpdesk.shared.objectValues.Pair;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

// Posteriormente, pode-se adicionar um ResultInteceptor (interface) com uma factory que fará o logging dos resultados
// como DomainErrors, Runtime Exceptions, etc...

/**
 * @param <T> Expected value
 * @apiNote Este ResultA<> atua com short-circuiting, ou seja, para a execução dos próximos códigos assim que identifica algum erro,
 * assim reduzindo custos de hardware para processar longos stacktraces...
 * @
 */
public abstract class ResultB<T> {
	protected T obj;
	protected DomainError error;

	abstract public ResultB<T> map(Supplier<T> value);
	abstract public  <O> ResultB<O> map(Function<T, O> value);
	abstract public <O> ResultB<O> flatMap(Supplier<ResultB<O>> value);
	abstract public <O> ResultB<O> flatMap(Function<T, ResultB<O>> value);
	abstract public ResultB<T> mapIfError(Consumer<DomainError> error);
	abstract public <O> ResultB<O> doOnNext(Supplier<O> value);
	abstract public T getOrDefault(T other);
	abstract public <T> ResultB<T> boxingError();
	abstract public Pair<T, DomainError> get();
	//abstract public ResultB<T> intercept(ResultB<?> interceptor);
	/**
	 * @param runnable Realiza a tarefa com suporte para tratamento á exceptions, fornecido pelo encaixotamento de Runnables
	 * @return ResultA encapsulando o valor ou o erro gerado nessa chamada
	 */
	abstract protected <O> ResultB<O> attempt(Supplier<O> runnable);
	abstract public boolean hasErrors();

	public static ResultB<Void> create() {
		return new ResultShortCircuit<>();
	}
	public static <T> ResultB<T> error(DomainError error) {
		return new ResultShortCircuit<>(error);
	}
	public static <T> ResultB<T> of(T obj) {
		return new ResultShortCircuit<>(obj);
	}
	public static ResultB<Void> empty() {
		return new ResultShortCircuit<>();
	}

	private ResultB(T obj, DomainError error) {
		this.obj = obj;
		this.error = error;
	}
	private ResultB(T obj) {
		this.obj = obj;
	}
	private ResultB(DomainError error) {
		this.error = error;
		obj = null;
	}
	private ResultB() {
		obj = null;
	}

	// Default implementation
	protected static class ResultShortCircuit<T> extends ResultB<T> {
		// <editor-fold defaultstate="collapsed" desc="Construtores padrões desta classe">
		private ResultShortCircuit(T obj, DomainError error) {
			super(obj, error);
		}
		private ResultShortCircuit(T obj) {
			super(obj);
		}
		private ResultShortCircuit(DomainError error) {
			super(error);
		}
		private ResultShortCircuit() {

		}
		// </editor-fold>

		// <editor-fold defaultstate="collapsed" desc="Overrides">
		@Override
		public ResultB<T> map(Supplier<T> value) {
			return processSupplier(value);
		}
		@Override
		public <O> ResultB<O> map(Function<T, O> func) {
			return processFunction(func);
		}
		@Override
		public <O> ResultB<O> flatMap(Supplier<ResultB<O>> value) {
			return flattenProcessSupplier(value);
		}
		@Override
		public <O> ResultB<O> flatMap(Function<T, ResultB<O>> value) {
			return flattenProcessFunction(value);
		}
		@Override
		public ResultB<T> mapIfError(Consumer<DomainError> whenError) {
			whenError.accept(error);
			return this;
		}
		@Override
		public <O> ResultB<O> doOnNext(Supplier<O> supplier) {
			if (hasErrors()) {
				return new ResultShortCircuit<>(error);
			}

			var value = supplier.get();
			return new ResultShortCircuit<>(value);
		}
		@Override
		protected <O> ResultB<O> attempt(Supplier<O> runnable) {
			try {
				var value = runnable.get();

				if (value instanceof DomainError err) {
					return new ResultShortCircuit<>(err);
				}

				return new ResultShortCircuit<>(value);
			} catch(RuntimeException e) {
				var err = new DomainError("Unexpected exception: ", e.getMessage());
				return new ResultShortCircuit<>(err);
			}
		}
		@Override
		public T getOrDefault(T other) {
			if (Objects.isNull(obj)) {
				return other;
			}

			return obj;
		}

		@Override
		public <T1> ResultB<T1> boxingError() {
			return new ResultShortCircuit<>(error);
		}

		@Override
		public Pair<T, DomainError> get() {
			return new Pair<T, DomainError>(obj, error);
		}

		@Override
		public boolean hasErrors() {
			return Objects.isNull(error);
		}
		// </editor-fold>

		// <editor-fold defaultstate="collapsed" desc="Helpers">
		protected <R> ResultB<R> processSupplier(Supplier<R> supplier) {
			return attempt(supplier);
		}
		protected <O> ResultB<O> processFunction(Function<T, O> func) {
			return attempt(() -> func.apply(obj));
		}
		protected <O> ResultB<O> flattenProcessFunction(Function<T, ResultB<O>> func) {
			return func.apply(obj);
		}
		protected <O> ResultB<O> flattenProcessSupplier(Supplier<ResultB<O>> supplier) {
			return supplier.get();
		}
		// </editor-fold>
	}
}
