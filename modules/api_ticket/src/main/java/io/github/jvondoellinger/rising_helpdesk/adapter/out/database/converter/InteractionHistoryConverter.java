package com.github.jvondoellinger.agp_protocol.api_ticket.adapter.out.database.converter;

import com.github.jvondoellinger.agp_protocol.shared_kernel.InteractionId;
import com.github.jvondoellinger.agp_protocol.api_ticket.domain.interaction.Interaction;
import com.github.jvondoellinger.agp_protocol.api_ticket.domain.interaction.InteractionsHistory;
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
