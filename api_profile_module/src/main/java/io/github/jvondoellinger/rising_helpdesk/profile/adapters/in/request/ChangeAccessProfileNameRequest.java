package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.request;

import java.util.UUID;

public record ChangeAccessProfileNameRequest(UUID id, String name) {
}
