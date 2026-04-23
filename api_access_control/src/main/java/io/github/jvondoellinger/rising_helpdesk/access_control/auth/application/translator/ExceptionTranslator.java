package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;

import java.util.function.Supplier;

public interface ExceptionTranslator<T> {
	Result<T, String> translate(Supplier<T> func);
}
