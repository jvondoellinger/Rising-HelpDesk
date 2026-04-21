package io.github.jvondoellinger.rising_helpdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@ConfigurationPropertiesScan
@SpringBootApplication
public class TicketRisingHelpdeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketRisingHelpdeskApplication.class, args);
	}

}
