package com.github.jvondoellinger.agp_protocol.application.queue.services;

import com.github.jvondoellinger.agp_protocol.application.queue.dtos.createQueue.CreateQueueRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.queue.dtos.createQueue.CreateQueueResponseDTO;
import com.github.jvondoellinger.agp_protocol.application.queue.mapper.QueueMapper;
import com.github.jvondoellinger.agp_protocol.application.queue.useCases.CreateQueueCommandUseCase;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateQueueCommandUseCasesImpl implements CreateQueueCommandUseCase {
	private final QueueRepository repository;
	private final QueueMapper mapper;

	@Override
	public CreateQueueResponseDTO execute(CreateQueueRequestDTO createQueueRequestDTO) {
		var mappedEntity = mapper.from(createQueueRequestDTO);
		var savedEntity = repository.save(mappedEntity);
		return mapper.toCreateResponse(savedEntity);
	}
}
