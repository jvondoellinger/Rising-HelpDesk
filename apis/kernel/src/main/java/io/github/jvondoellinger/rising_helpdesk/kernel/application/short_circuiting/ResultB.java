package io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting;

import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ResultB<T> {
	abstract public ResultB<T> map(Supplier<T> value);
	abstract public  <O> ResultB<O> map(Function<T, O> value);
	abstract public ResultB<T> flatMap(Supplier<T> value);
	abstract public <O> ResultB<O> flatMap(Function<T, ResultB<O>> value);
	abstract public <O> ResultB<O> mapIfError(Error error);
	abstract public <O> ResultB<O> doOnNext(Supplier<O> value);




	protected class ResultShortCircuit<T> extends ResultB<T> {
		private final LinkedList<ResultB<?>> results = new LinkedList<>();

		private ResultShortCircuit() {

		}

		@Override
		public ResultB<T> map(Supplier<T> value) {
			return processSupplier(value);
		}

		@Override
		public <O> ResultB<O> map(Function<T, O> func) {
			return processFunction(func);
		}

		@Override
		public ResultB<T> flatMap(Supplier<T> value) {
			return processSupplier(value);
		}

		@Override
		public <O> ResultB<O> flatMap(Function<T, ResultB<O>> value) {
			var out = value.apply();
			return processFunction(value);
		}

		@Override
		public <O> ResultB<O> mapIfError(Error error) {
			return null;
		}

		@Override
		public <O> ResultB<O> doOnNext(Supplier<O> value) {
			return null;
		}
		protected <R> ResultB<R> processSupplier(Supplier<R> supplier) {

		}
		protected <O> ResultB<O> processFunction(Function<T, O> value) {

		}
	}
}
