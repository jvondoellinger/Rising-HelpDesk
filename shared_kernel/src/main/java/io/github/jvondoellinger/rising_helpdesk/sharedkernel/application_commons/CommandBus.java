package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandBus {
	private final List<CommandUseCase<? extends Command, ?>> useCases;
	private final Map<Class<?>, CommandUseCase<? extends Command,?>> registry = new HashMap<>();

	protected CommandBus(List<CommandUseCase<? extends Command, ?>> useCases) {
		this.useCases = useCases;
	}

	@FixAfter
	public void dispatch(Object cmd) {
		return;
	}
}
