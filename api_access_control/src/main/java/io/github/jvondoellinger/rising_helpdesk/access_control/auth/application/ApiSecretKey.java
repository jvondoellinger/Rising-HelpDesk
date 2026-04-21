package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application;

import javax.crypto.SecretKey;

public interface ApiSecretKey {
	SecretKey getCurrent();
}
