package com.github.jvondoellinger.agp_protocol.shared_kernel;

public class AccessProfileId {
	private final DomainId id;

	private AccessProfileId(String value) {
		this.id = DomainId.parse(value);
	}
	private AccessProfileId(DomainId id) {
		this.id = id;
	}

	public static AccessProfileId of(DomainId id) {
		return new AccessProfileId(id);
	}
	public static AccessProfileId of(String id) {
		return new AccessProfileId(id);
	}

	private String value() {
		return id.value();
	}

	@Override
	public String toString() {
		return value();
	}
}
