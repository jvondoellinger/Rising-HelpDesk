package io.github.jvondoellinger.rising_helpdesk.sharedkernel;

public class UserProfileId {
	private final DomainId id;

	public UserProfileId() {
		this.id = DomainId.create();
	}

	private UserProfileId(String value) {
		this.id = DomainId.parse(value);
	}
	private UserProfileId(DomainId id) {
		this.id = id;
	}

	public static UserProfileId of(DomainId id) {
		return new UserProfileId(id);
	}
	public static UserProfileId of(String id) {
		return new UserProfileId(id);
	}

	private String value() {
		return id.value();
	}

	@Override
	public String toString() {
		return value();
	}
}
