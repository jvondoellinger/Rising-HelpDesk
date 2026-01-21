package com.github.jvondoellinger.agp_protocol.application_commons;

import com.github.jvondoellinger.agp_protocol.application.shared.id.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;

public interface QueryUseCase<O> {
	O queryById(DomainId Id);
	Pagination<O> queryByPagination(int page, int size);
}
