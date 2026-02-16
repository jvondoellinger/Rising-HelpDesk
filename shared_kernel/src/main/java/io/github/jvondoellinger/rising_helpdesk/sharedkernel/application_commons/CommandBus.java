package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CommandBus {
	private final List<CommandUseCase<? extends Command, ?>> useCases;
	private final Map<Class<?>, CommandUseCase<? extends Command,?>> registry = new HashMap<>();

	protected CommandBus(List<CommandUseCase<? extends Command, ?>> useCases) {
		this.useCases = useCases;
		index();
	}

	private Class<?> extractCommandType(Object useCase) {
		var interfaces = useCase.getClass().getGenericInterfaces();
		var clazz = CommandUseCase.class;

		for (var type : interfaces) {
			if (type instanceof ParameterizedType pt) {
				if (pt.getRawType().equals(clazz)) {
					return (Class<?>) pt.getActualTypeArguments()[0];
				}
			}
		}

		throw new IllegalStateException("Cannot resolve command type");
	}

	private void index() {
		for (var uc : useCases) {
			var cmdType = extractCommandType(uc);
			registry.put(cmdType, uc);
		}
	}

	@SuppressWarnings("unchecked")
	public <C extends Command> void dispatch(C cmd) {
		var useCase = (CommandUseCase<C, ?>) registry.get(cmd.getClass());

		useCase.execute(cmd);
	}
}
