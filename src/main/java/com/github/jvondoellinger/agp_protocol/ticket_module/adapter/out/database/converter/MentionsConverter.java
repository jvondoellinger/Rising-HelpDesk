package com.github.jvondoellinger.agp_protocol.ticket_module.adapter.out.database.converter;

import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.mention.Mentions;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Converter
public class MentionsConverter implements AttributeConverter<Mentions, String> {
	@Override
	public String convertToDatabaseColumn(Mentions attribute) {
		var sb = new StringBuilder();

		new ArrayList<>(attribute.readonlyList())
			   .stream()
			   .filter(Objects::nonNull)
			   .map(UserProfileId::toString)
			   .forEach(value -> {
				   sb.append(value).append(";");
			   });

		return sb.toString();
	}

	@Override
	public Mentions convertToEntityAttribute(String dbData) {
		var ids = Arrays.stream(dbData.split(";"))
			   .filter(String::isBlank)
			   .map(UserProfileId::of)
			   .toList();
		return new Mentions(ids);
	}
}
