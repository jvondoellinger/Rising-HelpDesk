package com.github.jvondoellinger.agp_protocol.shared_kernel.application_commons;

public interface CommandUseCase<I, O> {
	O execute(I request);
}
