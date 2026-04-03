package io.github.jvondoellinger.rising_helpdesk.ticket.repository.config;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.entities.QueueDbEntity;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.jpa.JpaQueueRepository;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableAutoConfiguration()
@ComponentScan(basePackages = "io.github.jvondoellinger.rising_helpdesk")
@EnableJpaRepositories(basePackageClasses = JpaQueueRepository.class)
@EntityScan(basePackageClasses = QueueDbEntity.class)
public class TestApplication {
}

