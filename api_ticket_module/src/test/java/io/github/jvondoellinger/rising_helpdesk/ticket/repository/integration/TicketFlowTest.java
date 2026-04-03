package io.github.jvondoellinger.rising_helpdesk.ticket.repository.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.CommandBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByPaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.repository.TicketRepositoryTest;
import io.github.jvondoellinger.rising_helpdesk.ticket.repository.config.IntegrationTestConfig;
import io.github.jvondoellinger.rising_helpdesk.ticket.repository.integration.builders.CreateQueueRequestTestBuilder;
import io.github.jvondoellinger.rising_helpdesk.ticket.repository.integration.builders.CreateTicketRequestTestBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = IntegrationTestConfig.class)
@AutoConfigureMockMvc
public class TicketFlowTest extends TicketRepositoryTest {
	@Autowired
	private CommandBus commandBus;
	@Autowired
	private QueryBus queryBus;
	@Autowired
	private CreateTicketRequestTestBuilder builder;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void should_create_ticket() throws Exception {
		var queueRequest = new CreateQueueRequestTestBuilder().build();
		var queueJson = toJson(queueRequest);

		mockMvc.perform(post("/api/queue")
					 .contentType(MediaType.APPLICATION_JSON)
					 .content(queueJson))
			   .andExpect(status().isAccepted());


		var queue = queryBus.send(new FindQueueByPaginationQuery(
			   0 , 1
		)).getValue().items().stream().findFirst().get();

		var tkRequest = builder.withQueueId(queue.id()).build();
		var tkJson = toJson(tkRequest);


		mockMvc.perform(post("/api/ticket")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(tkJson))
		   .andExpect(status().isAccepted());
	}

	private String toJson(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
