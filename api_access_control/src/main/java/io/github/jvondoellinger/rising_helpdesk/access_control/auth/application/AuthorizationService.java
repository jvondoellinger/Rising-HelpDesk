package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application;

import io.github.jvondoellinger.rising_helpdesk.access_control.auth.domain.TokenContent;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.entities.Permission;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AuthorizationService implements PrefixCreatorTemplate {
	private final StringRedisTemplate template;
	private final StringPermissionsCodec codec;
	private static final String prefix = "accessprofile:permission:";

	// verificar se as permissoes do token condizem com as permissoes do accessprofile?
	// Controller informando as permissoes necessarias?
	// Uma classe que compara as permissoes necessarias de um Token com as que foram definidas estaticamente via codigo para realizar a liberacao
	public boolean confirmAuthorization(TokenContent content, Permission... requiredPermissions) {
		var permissions = requiredPermissions;

		var required = Set.of(requiredPermissions);

		return content.getAccessProfileIds()
			   .stream()
			   .anyMatch(accessProfileId -> {
				   var permissionFromProfile = template
						 .opsForValue()
						 .get(createPrefix(accessProfileId.toString()));

				   if (permissionFromProfile == null) {
					   return false;
				   }

				   var permissionList = codec.decode(permissionFromProfile);
				   var permissionSet = new HashSet<>(permissionList);

				   return permissionSet.containsAll(required);
			   });
	}

	public String createPrefix(String data) {
		return prefix + data;
	}
}
