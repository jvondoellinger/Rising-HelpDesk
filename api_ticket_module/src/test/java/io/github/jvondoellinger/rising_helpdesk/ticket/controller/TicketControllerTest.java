package io.github.jvondoellinger.rising_helpdesk.ticket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.TicketController;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ticket.CreateTicketRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.CommandBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper.TicketResponseMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketController.class)
@ContextConfiguration(classes = TicketController.class)
class TicketControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CommandBus commandBus;

	@MockBean
	private QueryBus queryBus;

	@MockBean
	private TicketResponseMapper responseMapper;

	@Test
	void should_return_bad_request_when_command_fails() throws Exception {
		var request = new CreateTicketRequest(
			   "TEST_TITLE",
			   UUID.fromString("00000000-0000-0000-0000-000000000000"),
			   LocalDateTime.now()
		);

		when(commandBus.send(any()))
			   .thenReturn(ResultV1.success());

		var json = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/ticket")
					 .contentType(MediaType.APPLICATION_JSON)
					 .content(json))
			   .andExpect(status().isAccepted());
	}

	@Test
	void should_return_ok_when_command_succeeds() throws Exception {
		var request = new CreateTicketRequest(
			   "TEST_TITLE",
			   UUID.fromString("00000000-0000-0000-0000-000000000000"),
			   LocalDateTime.now()
		);

		when(commandBus.send(any()))
			   .thenReturn(ResultV1.success());

		var json = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/ticket")
					 .contentType(MediaType.APPLICATION_JSON)
					 .content(json))
			   .andExpect(status().isAccepted());
	}
}