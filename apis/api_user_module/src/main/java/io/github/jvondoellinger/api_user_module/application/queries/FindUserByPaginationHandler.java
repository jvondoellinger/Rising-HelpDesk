package io.github.jvondoellinger.api_user_module.application.queries;

import io.github.jvondoellinger.api_user_module.domain.User;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.QueryHandler;

import java.util.List;

public interface FindUserByPaginationHandler extends QueryHandler<FindUserByPagination, List<User>> {
}
