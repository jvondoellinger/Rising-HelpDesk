package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.mappers.HttpResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.request.ChangePermissionCodeRequest;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.request.CreatePermissionRequest;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.permissions.ChangePermissionCode;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.permissions.CreatePermissionCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.bus.CommandBus;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.permission.FindPermissionPaginationQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permission")
@AllArgsConstructor
public class PermissionController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;
    private final HttpResponseMapper httpResponseMapper;

    @GetMapping
    public ResponseEntity<?> get(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        var query = new FindPermissionPaginationQuery(page, size);
        var result = queryBus.send(query);

        return httpResponseMapper.fromResult(result);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody CreatePermissionRequest request) {
        var cmd = new CreatePermissionCommand(request.permission());
        var result = commandBus.send(cmd);

        return httpResponseMapper.fromResult(result);
    }

    @PutMapping("{permission}")
    public ResponseEntity<?> replace(
            @PathVariable("permission") String permission,
            @RequestBody ChangePermissionCodeRequest request) {
        var cmd = new ChangePermissionCode(request.code());
        var result = commandBus.send(cmd);

        return httpResponseMapper.fromResult(result);
    }
}
