package io.github.jvondoellinger.rising_helpdesk.kernel.domain;

public interface DomainRule<T> {
	boolean isBroken();
}
