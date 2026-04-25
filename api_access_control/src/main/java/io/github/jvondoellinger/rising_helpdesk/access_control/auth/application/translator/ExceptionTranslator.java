package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.translator;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;

import java.util.function.Supplier;

public interface ExceptionTranslator<T> {
	ResultV1<T, String> translate(Supplier<T> func);
}
