package io.github.jvondoellinger.rising_helpdesk.access_control.adapters.in;

import io.github.jvondoellinger.rising_helpdesk.access_control.adapters.in.request.CreatePermissionRequest;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.commands.permissions.CreatePermissionCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.handlers.bus.CommandBus;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permission")
@AllArgsConstructor
public class PermissionController {
    private final CommandBus commandBus;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody CreatePermissionRequest request) {
        commandBus.send(new CreatePermissionCommand(request.permission()));
        return ResponseEntity.ok("");
    }
}
