package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.request;

import java.util.List;

public record CreateAccessProfileRequest(String name, List<String> permissions) {
}
