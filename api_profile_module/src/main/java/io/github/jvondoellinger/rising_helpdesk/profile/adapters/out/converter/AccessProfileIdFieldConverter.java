package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.converter;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AccessProfileIdFieldConverter implements AttributeConverter<AccessProfileId, String> {
	@Override
	public String convertToDatabaseColumn(AccessProfileId attribute) {
		return attribute.toString();
	}

	@Override
	public AccessProfileId convertToEntityAttribute(String dbData) {
		return AccessProfileId.of(dbData);
	}
}
