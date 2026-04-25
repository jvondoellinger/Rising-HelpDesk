package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;

import java.util.function.Supplier;

public interface ExceptionTranslator<T> {
	Result<T> translate(Supplier<T> func);
}
