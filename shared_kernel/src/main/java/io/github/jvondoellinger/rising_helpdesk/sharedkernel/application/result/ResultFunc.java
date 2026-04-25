package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.abstraction;

@FunctionalInterface
public interface ResultFunc<I, O> {
	O run(I supplier);
}
