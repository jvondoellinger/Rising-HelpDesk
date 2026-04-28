package io.github.jvondoellinger.rising_helpdesk.kernel;

import java.util.ArrayList;
import java.util.List;

public abstract class DomainCollection<T> {
	protected final List<T> values;

	public DomainCollection(List<T> values) {
		this.values = new ArrayList<>(values);
	}

	public DomainCollection() {
		this.values = new ArrayList<>();
	}

	public void add(T value) {
		values.add(value);
	}
	public void add(List<T> value) {
		values.addAll(value);
	}
	public List<T> readonlyList() {
		return List.copyOf(values);
	}
}
