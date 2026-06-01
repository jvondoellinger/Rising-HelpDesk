package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.request;

import java.util.List;
import java.util.UUID;

public record AddAccessProfilePermissionRequest(UUID id, List<String> permissions) {
}
