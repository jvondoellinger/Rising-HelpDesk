package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.converter;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.interaction.Interaction;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.interaction.InteractionsHistory;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.InteractionId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class InteractionHistoryConverter implements AttributeConverter<InteractionsHistory, String> {
	@Override
	public String convertToDatabaseColumn(InteractionsHistory attribute) {
		if (attribute == null || attribute.readonlyList().isEmpty()) {
			return "";
		}

		var sb = new StringBuilder();

		for (var id : attribute.readonlyList()) {
			sb.append(id.getId().toString())
			  .append(";");
		}

		return sb.toString();
	}

	@Override
	public InteractionsHistory convertToEntityAttribute(String dbData) {
		var interaction = Arrays.stream(dbData.split(";"))
			   .filter(s -> !s.isBlank())
			   .map(s ->
				   new Interaction(
						 InteractionId.of(s),
						 null,
						 true,
						 null,
						 null
				   )
			   )
			   .toList();

		return new InteractionsHistory(interaction);
	}
}
