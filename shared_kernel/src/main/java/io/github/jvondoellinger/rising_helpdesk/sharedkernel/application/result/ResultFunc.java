package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result;

@FunctionalInterface
	public interface ResultFunc<I, O> {
	O run(I supplier);
}
