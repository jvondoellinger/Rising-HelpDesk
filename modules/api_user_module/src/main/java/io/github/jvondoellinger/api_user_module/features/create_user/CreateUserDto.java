package io.github.jvondoellinger.api_user_module.features.create_user;

public record CreateUserDto(String nickname,
                            String email,
                            String password) {
}
