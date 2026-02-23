package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandBus {
	private final List<CommandHandler<? extends Command>> useCases;
	private final Map<Class<?>, CommandHandler<? extends Command>> registry = new HashMap<>();

	protected CommandBus(List<CommandHandler<? extends Command>> useCases) {
		this.useCases = useCases;
	}

	@FixAfter
	public void dispatch(Object cmd) {
		return;
	}
}
