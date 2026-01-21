package com.github.jvondoellinger.agp_protocol.application.queue.useCases;

import com.github.jvondoellinger.agp_protocol.application.queue.dtos.createQueue.CreateQueueRequestDTO;
import com.github.jvondoellinger.agp_protocol.application.queue.dtos.createQueue.CreateQueueResponseDTO;
import com.github.jvondoellinger.agp_protocol.application_commons.CommandUseCase;

public interface CreateQueueCommandUseCase extends CommandUseCase<CreateQueueRequestDTO, CreateQueueResponseDTO> {
}
