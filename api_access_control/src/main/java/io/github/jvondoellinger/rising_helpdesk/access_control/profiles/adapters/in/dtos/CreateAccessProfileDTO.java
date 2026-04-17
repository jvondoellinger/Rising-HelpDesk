package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.dtos;

import java.util.List;

public record CreateAccessProfileDTO(String name, List<String> permissions) {
}
