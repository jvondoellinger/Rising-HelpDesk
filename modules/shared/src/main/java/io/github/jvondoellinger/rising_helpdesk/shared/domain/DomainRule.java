package io.github.jvondoellinger.rising_helpdesk.shared.domain;

public interface DomainRule<T> {
	boolean isBroken();
}
