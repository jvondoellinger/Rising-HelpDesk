package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.dtos;

import java.util.List;

public record CreateAccessProfileDTO(String name, List<String> permissions) {
}
