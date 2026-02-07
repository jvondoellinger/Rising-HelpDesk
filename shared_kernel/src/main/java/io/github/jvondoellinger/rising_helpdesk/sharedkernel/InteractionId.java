package io.github.jvondoellinger.rising_helpdesk.sharedkernel;

public class InteractionId {
	private final DomainId id;

	public InteractionId() {
		this.id = DomainId.create();
	}

	private InteractionId(String value) {
		this.id = DomainId.parse(value);
	}
	private InteractionId(DomainId id) {
		this.id = id;
	}

	public static InteractionId of(DomainId id) {
		return new InteractionId(id);
	}
	public static InteractionId of(String id) {
		return new InteractionId(id);
	}

	private String value() {
		return id.value();
	}

	@Override
	public String toString() {
		return value();
	}
}
