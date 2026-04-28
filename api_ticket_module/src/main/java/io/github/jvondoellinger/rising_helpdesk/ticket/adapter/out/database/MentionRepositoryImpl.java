package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.kernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.jpa.JpaMentionRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Mention;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.MentionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MentionRepositoryImpl implements MentionRepository {
	private final JpaMentionRepository jpaMentionRepository;

	@Override
	public void save(Mention entity) {

	}

	@Override
	public void update(Mention entity) {

	}

	@Override
	public void delete(Mention entity) {

	}

	@Override
	public Optional<Mention> findById(UUID uuid) {
		return Optional.empty();
	}

	@Override
	public boolean existsById(UUID uuid) {
		return false;
	}

	@Override
	public Pagination<Mention> findByPagination(PaginationFilter filter) {
		return null;
	}

	@Override
	public long total() {
		return 0;
	}
}
