package io.github.jvondoellinger.rising_helpdesk.sharedkernel.infra_commons;

@FunctionalInterface
public interface DbEntity<DomainEntity> {
	DomainEntity toDomainEntity();
}
