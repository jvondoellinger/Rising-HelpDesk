package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.CommandHandler;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CloseTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class CloseTicketCommandService implements CommandHandler<CloseTicketCommand> {
	private final TicketRepository repository;

	@Override
	public ResultTransformerStep<Void> handle(CloseTicketCommand cmd) {
		return ResultTransformerStep.create()
			   .flatMap(aVoid -> {
				   var optionalTk = repository.findById(cmd.ticketId());
				   if (optionalTk.isEmpty()) {
					   return Result.error(new DomainError("NO_TICKET_FOUND", "No ticket found."));
				   }

				   var tk = optionalTk.get();
				   tk.close();
				   repository.save(tk);

				   return Result.success();
			   });
	}

	@Override
	public Class<CloseTicketCommand> getCommandType() {
		return CloseTicketCommand.class;
	}
}
