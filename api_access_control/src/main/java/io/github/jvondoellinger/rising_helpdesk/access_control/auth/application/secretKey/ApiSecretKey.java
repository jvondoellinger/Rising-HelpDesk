package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.secretKey;

import javax.crypto.SecretKey;

public interface ApiSecretKey {
	SecretKey getCurrent();
}
