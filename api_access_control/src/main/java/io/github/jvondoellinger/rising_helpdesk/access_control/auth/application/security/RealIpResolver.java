package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.security;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;

import java.net.InetAddress;

public interface RealIpResolver {
	Result<Address> resolve(InetAddress source);
}
