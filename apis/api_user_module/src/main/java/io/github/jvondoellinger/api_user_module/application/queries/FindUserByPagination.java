package io.github.jvondoellinger.api_user_module.application.queries;

import io.github.jvondoellinger.api_user_module.domain.User;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.Query;

import java.util.List;

public record FindUserByPagination(int size, int page) implements Query<List<User>> {
}
