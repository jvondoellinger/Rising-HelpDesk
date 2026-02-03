package com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons;

@FunctionalInterface
public interface DbEntity<DomainEntity> {
	DomainEntity toDomainEntity();
}
