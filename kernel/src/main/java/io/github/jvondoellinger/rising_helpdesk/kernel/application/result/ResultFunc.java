package io.github.jvondoellinger.rising_helpdesk.kernel.application.result;

@FunctionalInterface
	public interface ResultFunc<I, O> {
	O run(I supplier);
}
