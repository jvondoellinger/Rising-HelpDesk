package com.github.jvondoellinger.agp_protocol.application_commons;

import com.github.jvondoellinger.agp_protocol.application.shared.id.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.shared_kernel.QueryFilter;

public interface QueryUseCase<O, I extends DomainIdDTO> {
	O queryById(I Id);
	Pagination<O> queryByPagination(int page, int size);
}
