package io.github.jvondoellinger.rising_helpdesk.shared.application.objectValues;

public class Pair<P, V> {
	private final P value1;
	private final V value2;

	public Pair(P value1, V value2) {
		this.value1 = value1;
		this.value2 = value2;
	}

	public P getValue1() {
		return value1;
	}

	public V getValue2() {
		return value2;
	}
}
