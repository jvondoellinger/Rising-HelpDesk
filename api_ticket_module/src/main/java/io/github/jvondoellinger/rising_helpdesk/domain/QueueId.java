package io.github.jvondoellinger.rising_helpdesk.domain;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainId;

public class QueueId {
	private final DomainId id;

	public QueueId() {
		this.id = DomainId.create();
	}

	private QueueId(String value) {
		this.id = DomainId.parse(value);
	}
	private QueueId(DomainId id) {
		this.id = id;
	}

	public static QueueId of(DomainId id) {
		return new QueueId(id);
	}
	public static QueueId of(String id) {
		return new QueueId(id);
	}

	private String value() {
		return id.value();
	}

	@Override
	public String toString() {
		return value();
	}
}
