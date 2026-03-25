package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Profile({"dev","test"})
public class CurrentUserServiceMockImpl implements CurrentUserService {
	@Override
	public UUID getUserId() {
		var uuid = UUID.fromString("8288c453-c96a-4f50-b636-1a785b85bdc8");
		System.out.println(uuid);
		return uuid;
	}
}
