package com.github.jvondoellinger.agp_protocol.ticket_module.adapter.out.database.converter;

import com.github.jvondoellinger.agp_protocol.ticket_module.domain.QueueId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class QueueIdFieldConverter
	implements AttributeConverter<QueueId, String> {
	@Override
	public String convertToDatabaseColumn(QueueId attribute) {
		return attribute.toString();
	}

	@Override
	public QueueId convertToEntityAttribute(String dbData) {
		return QueueId.of(dbData);
	}
}
