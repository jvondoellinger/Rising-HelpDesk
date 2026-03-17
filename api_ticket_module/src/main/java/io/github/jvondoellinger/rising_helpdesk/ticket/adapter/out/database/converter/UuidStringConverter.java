package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

import java.util.UUID;

@Convert
public class UuidStringConverter implements AttributeConverter<UUID, String> {
    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        return uuid.toString();
    }

    @Override
    public UUID convertToEntityAttribute(String s) {
        return UUID.fromString(s);
    }
}
