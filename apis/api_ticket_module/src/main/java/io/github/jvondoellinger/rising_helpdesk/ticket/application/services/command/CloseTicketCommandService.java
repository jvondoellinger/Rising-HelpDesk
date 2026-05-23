package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.CommandHandler;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ticket.CloseTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class CloseTicketCommandService implements CommandHandler<CloseTicketCommand> {
	private final TicketRepository repository;

	@Override
	public ResultB<Void> handle(CloseTicketCommand cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var optionalTk = repository.findById(cmd.ticketId());
				   if (optionalTk.isEmpty()) {
					   return ResultB.error(new DomainError("NO_TICKET_FOUND", "No ticket found."));
				   }

				   var tk = optionalTk.get();
				   tk.close();
				   repository.save(tk);

				   return ResultB.create();
			   });
	}

	@Override
	public Class<CloseTicketCommand> getCommandType() {
		return CloseTicketCommand.class;
	}
}
