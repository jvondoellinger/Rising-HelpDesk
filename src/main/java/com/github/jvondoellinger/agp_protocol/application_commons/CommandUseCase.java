package com.github.jvondoellinger.agp_protocol.application_commons;

public interface CommandUseCase<I, O> {
	O execute(I request);
}
