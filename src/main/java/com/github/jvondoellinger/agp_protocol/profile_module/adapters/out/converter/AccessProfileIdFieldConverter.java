package com.github.jvondoellinger.agp_protocol.profile_module.adapters.out.converter;

import com.github.jvondoellinger.agp_protocol.shared_kernel.AccessProfileId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
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
