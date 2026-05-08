package io.github.jvondoellinger.rising_helpdesk.kernel.domain;

import java.util.List;

public abstract class DomainEntity<T> {
	private final List<DomainRule<T>> rules;

	protected DomainEntity(List<DomainRule<T>> rules) {
		this.rules = rules;
	}

	public boolean isBroken() {
		return rules
			   .stream()
			   .anyMatch(DomainRule::isBroken);
	}

	public void addRule(DomainRule<T> rule) {
		if (rule == null) return;
		if (rules == null) return;

		rules.add(rule);
	}
}

