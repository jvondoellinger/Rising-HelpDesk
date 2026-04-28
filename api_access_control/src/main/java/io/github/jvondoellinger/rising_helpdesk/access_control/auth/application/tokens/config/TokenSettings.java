package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties(prefix = "auth.jwt")
public class TokenSettings {
	private int maxTokensPerUser;
	private Duration timeout;
}


