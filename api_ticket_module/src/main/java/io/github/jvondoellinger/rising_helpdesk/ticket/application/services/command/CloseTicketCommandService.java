package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.CommandHandler;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CloseTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CloseTicketCommandService implements CommandHandler<CloseTicketCommand> {
	private final TicketRepository repository;

	@Override
	public Result<Void, String> handle(CloseTicketCommand cmd) {
		var optionalTk = repository.findById(cmd.ticketId());
		if (optionalTk.isEmpty()) {
			return Result.failure("No ticket found.");
		}

		var tk = optionalTk.get();
		tk.close();
		repository.save(tk);

		return Result.success();
	}

	@Override
	public Class<CloseTicketCommand> getCommandType() {
		return CloseTicketCommand.class;
	}
}
