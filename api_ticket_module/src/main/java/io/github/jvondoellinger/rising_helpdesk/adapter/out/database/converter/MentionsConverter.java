package io.github.jvondoellinger.rising_helpdesk.adapter.out.database.converter;

import io.github.jvondoellinger.rising_helpdesk.domain.mention.Mentions;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
