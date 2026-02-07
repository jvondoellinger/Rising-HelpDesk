package io.github.jvondoellinger.rising_helpdesk.adapter.out.database.converter;

import io.github.jvondoellinger.rising_helpdesk.domain.QueueId;
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
