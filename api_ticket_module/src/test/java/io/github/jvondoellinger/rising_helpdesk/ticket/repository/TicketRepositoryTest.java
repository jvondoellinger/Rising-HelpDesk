package io.github.jvondoellinger.rising_helpdesk.ticket.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class TicketRepositoryTest {

	@Container
	static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
		   .withDatabaseName("RisingHeldDesk_TEST")
		   .withUsername("test")
		   .withPassword("test");
}
