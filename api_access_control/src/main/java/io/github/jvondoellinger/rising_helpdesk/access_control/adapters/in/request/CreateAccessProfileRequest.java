package io.github.jvondoellinger.rising_helpdesk.access_control.adapters.in.request;

import java.util.List;
import java.util.UUID;

public record CreateAccessProfileRequest(String name, List<UUID> permissionIds) {
}
