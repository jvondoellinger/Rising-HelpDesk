package io.github.jvondoellinger.rising_helpdesk.adapter.out.database.converter;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserProfileIdFieldConverter implements AttributeConverter<UserProfileId, String> {
	@Override
	public String convertToDatabaseColumn(UserProfileId attribute) {
		return attribute.toString();
	}

	@Override
	public UserProfileId convertToEntityAttribute(String dbData) {
		return UserProfileId.of(dbData);
	}
}
