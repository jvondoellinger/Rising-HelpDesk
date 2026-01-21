package com.github.jvondoellinger.agp_protocol.infra_commons;

@FunctionalInterface
public interface DbEntity<DomainEntity> {
	DomainEntity toDomainEntity();
}
