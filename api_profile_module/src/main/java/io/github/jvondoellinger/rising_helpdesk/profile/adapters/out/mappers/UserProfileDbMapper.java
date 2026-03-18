package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.mappers;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.entities.AccessProfileDbEntity;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.entities.UserProfileDbEntity;
import org.springframework.stereotype.Service;

@Service
public class UserProfileDbMapper {
	public UserProfileDbEntity from(UserProfile userProfile) {
		return new UserProfileDbEntity(
			   userProfile.getUserId(),
			   new AccessProfileDbEntity(userProfile.getAccessProfile(), null, null, null, null),
			   userProfile.getCreatedAt(),
			   userProfile.getUpdatedAt()
		);
	}

	/**
	 * Atualiza o perfil do usuário.
	 *
	 * @param userProfileDbEntity ATENÇÃO: Entidade vinda do banco de dados tem que estar configurada em EAGER, senão, garantir que accessProfile não venha null!
	 */
	public UserProfile toUserProfile(UserProfileDbEntity userProfileDbEntity) {
		return new UserProfile(
			   userProfileDbEntity.getUserId(),
			   userProfileDbEntity.getAccessProfile().getId(),
			   userProfileDbEntity.getCreatedAt(),
			   userProfileDbEntity.getUpdatedAt()
		);
	}
}
