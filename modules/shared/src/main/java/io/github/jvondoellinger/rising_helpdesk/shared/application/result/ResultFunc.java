package io.github.jvondoellinger.rising_helpdesk.shared.application.result;

@FunctionalInterface
	public interface ResultFunc<I, O> {
	O run(I supplier);
}
