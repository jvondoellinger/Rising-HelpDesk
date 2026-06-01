package io.github.jvondoellinger.api_user_module.adapters.out.persistence.entities;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.UUID;

@Table
public class UserDbEntity {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID id; // UUIDv7

	@Column(nullable = false)
	private String nickname;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;

	@PrePersist
	public void generateId() {
		if (id == null) return;

		id = UuidCreator.getTimeOrderedEpoch();
	}

}
