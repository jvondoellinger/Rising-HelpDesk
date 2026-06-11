package io.github.jvondoellinger.risinghelpdesk.ticket.infrastructure.configs;

import io.github.jvondoellinger.risinghelpdesk.ticket.infrastructure.serializer.cache.ResultRedisSerializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
@EnableCaching
public class RedisConfig {

	@Bean
	public RedisCacheManager cacheManager(
		   RedisConnectionFactory connectionFactory,
		   ResultRedisSerializer resultRedisSerializer) {

		var config = RedisCacheConfiguration.defaultCacheConfig()
			   .entryTtl(Duration.of(30, ChronoUnit.MINUTES))
			   .disableCachingNullValues()
			   .serializeValuesWith(
					 RedisSerializationContext.SerializationPair.fromSerializer(resultRedisSerializer)
			   );

		return RedisCacheManager.builder(connectionFactory)
			   .cacheDefaults(config)
			   .build();
	}
}
