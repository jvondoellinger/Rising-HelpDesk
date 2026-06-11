package io.github.jvondoellinger.risinghelpdesk.ticket.infrastructure.serializer.cache;

import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@AllArgsConstructor
public class ResultRedisSerializer implements RedisSerializer<ResultB<?>> {
	private final ObjectMapper mapper;

	@Override
	public byte[] serialize(@Nullable ResultB<?> value) throws SerializationException {
		if (value.hasErrors()) {
			return null;
		}

		var resultValue = value.getOrDefault(null);

		if (resultValue == null) {
			return null;
		}

		return mapper.writeValueAsBytes(resultValue);
	}

	@Override
	public @Nullable ResultB<?> deserialize(byte @Nullable [] bytes) throws SerializationException {
		var value = mapper.convertValue(bytes, Object.class);

		return ResultB.of(value);
	}
}
