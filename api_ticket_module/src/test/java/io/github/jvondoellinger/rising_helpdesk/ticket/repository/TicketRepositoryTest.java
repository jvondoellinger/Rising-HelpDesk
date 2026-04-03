package io.github.jvondoellinger.rising_helpdesk.ticket.repository;

import io.github.jvondoellinger.rising_helpdesk.ticket.repository.config.TestApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(classes = TestApplication.class)
@Testcontainers
public abstract class TicketRepositoryTest {

	@Container
	static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.33")
		   .withDatabaseName("test")
		   .withUsername("root")
		   .withPassword("password")
		   .withReuse(true);

	@DynamicPropertySource
	static void configure(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mysql::getJdbcUrl);
		registry.add("spring.datasource.username", mysql::getUsername);
		registry.add("spring.datasource.password", mysql::getPassword);
		registry.add("spring.datasource.driver-class-name", () -> "com.mysql.cj.jdbc.Driver");
		registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
		registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.MySQLDialect");
		registry.add("spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults", () -> "false");


	}

	// Opcional: força o start do container antes de tudo
	static {
		mysql.start();
	}

}
