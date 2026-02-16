package io.github.jvondoellinger.rising_helpdesk.ticket.infrastructure;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.converter.UserProfileIdFieldConverter;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.interaction.Interaction;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_interaction")
@Getter
@Setter
@FixAfter
public class InteractionDbEntity {
	@Id
	private String domainId;
	private String text;

	private boolean visible;

	@Column(name = "interacted_by_id")
	@Convert(converter = UserProfileIdFieldConverter.class)
	private UserProfileId interactedBy;

	@CreationTimestamp
	private LocalDateTime interactedOn;

	public InteractionDbEntity(Interaction interaction) {
		this.domainId = interaction.getId().toString();
		this.text = interaction.getText();
		this.visible = interaction.isVisible();
		this.interactedBy = interaction.getInteractedBy();
		this.interactedOn = interaction.getInteractedOn();
	}

	@PersistenceCreator
	public InteractionDbEntity(String domainId, String text, boolean visible, UserProfileId interactedBy, LocalDateTime interactedOn) {
		this.domainId = domainId;
		this.text = text;
		this.visible = visible;
		this.interactedBy = interactedBy;
		this.interactedOn = interactedOn;
	}

	protected InteractionDbEntity() {}

	public static InteractionDbEntity foreignKey(String id) {
		var interactionDbEntity = new InteractionDbEntity();
		interactionDbEntity.setDomainId(id);
		return interactionDbEntity;
	}

	public static List<InteractionDbEntity> foreignKey(List<String> ids) {
		var interactions = new ArrayList<InteractionDbEntity>();

		InteractionDbEntity interactionDbEntity;

		for (var id : ids) {
			interactionDbEntity = new InteractionDbEntity();
			interactionDbEntity.setDomainId(id);
		}

		return interactions;
	}
}
