package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.RemoveTicketMentionCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.RemoveTicketMentionHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security.CurrentUserService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Mention;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.MentionRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class RemoveTicketMentionService implements RemoveTicketMentionHandler {
	private final TicketRepository ticketRepository;
	private final MentionRepository mentionRepository;
	private final CurrentUserService currentUserService;

	@Override
	public ResultTransformerStep<Void> handle(RemoveTicketMentionCommand cmd) {
		return ResultTransformerStep.create()
			   .flatMap(aVoid -> {
				   if (ticketRepository.existsById(cmd.ticketId())) {
					   return Result.error(new DomainError("NO_TICKET_FOUND", "No ticket found."));
				   }

				   var mention = new Mention(
						 cmd.userId(),
						 currentUserService.getUserId(),
						 cmd.ticketId()
				   );

				   mentionRepository.save(mention);
				   return Result.success();
			   });
	}

	@Override
	public Class<RemoveTicketMentionCommand> getCommandType() {
		return RemoveTicketMentionCommand.class;
	}
}
