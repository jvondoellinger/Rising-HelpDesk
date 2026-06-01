package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.translator;

import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;

import java.util.function.Supplier;

public interface ExceptionTranslator<T> {
	ResultB<T> translate(Supplier<T> func);
}
