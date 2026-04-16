package io.github.jvondoellinger.rising_helpdesk.access_control.adapters.in.request;

import java.util.List;
import java.util.UUID;

public record RemoveAccessProfilePermissionRequest(UUID id, List<String> permissions) {
}
