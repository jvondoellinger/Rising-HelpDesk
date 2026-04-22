package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenPayload;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.entities.Permission;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AuthorizationService {
	private final StringRedisTemplate template;
	private final StringPermissionsCodec codec;

	// verificar se as permissoes do token condizem com as permissoes do accessprofile?
	// Controller informando as permissoes necessarias?
	// Uma classe que compara as permissoes necessarias de um Token com as que foram definidas estaticamente via codigo para realizar a liberacao
	public boolean confirmAuthorization(TokenPayload content, Permission... requiredPermissions) {
		return false;
	}
}
