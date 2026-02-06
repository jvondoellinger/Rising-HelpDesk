package com.github.jvondoellinger.agp_protocol.api_ticket.adapter.out.database.converter;

import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;
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
