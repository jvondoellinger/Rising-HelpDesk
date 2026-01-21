package com.github.jvondoellinger.agp_protocol.ticket_module.adapter.out.database.converter;

import com.github.jvondoellinger.agp_protocol.ticket_module.domain.valueObjects.TicketNumber;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TicketNumberConverter implements AttributeConverter<TicketNumber, String> {
	@Override
	public String convertToDatabaseColumn(TicketNumber ticketNumber) {
		return ticketNumber.toString();
	}

	@Override
	public TicketNumber convertToEntityAttribute(String s) {
		return TicketNumber.parse(s);
	}
}
