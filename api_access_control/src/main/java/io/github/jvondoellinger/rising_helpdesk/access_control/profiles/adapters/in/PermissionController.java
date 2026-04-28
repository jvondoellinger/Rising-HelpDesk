package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.request.ChangePermissionCodeRequest;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.request.CreatePermissionRequest;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.permissions.ChangePermissionCode;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.permissions.CreatePermissionCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.bus.CommandBus;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.permission.FindPermissionPaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permission")
@AllArgsConstructor
public class PermissionController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    @GetMapping
    public Result<?> get(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        var query = new FindPermissionPaginationQuery(page, size);
        var result = queryBus.send(query);

        return result;
    }

    @PostMapping
    public Result<?> post(@RequestBody CreatePermissionRequest request) {
        var cmd = new CreatePermissionCommand(request.permission());
        var result = commandBus.send(cmd);

        return result;
    }

    @PutMapping("{permission}")
    public Result<?> replace(
            @PathVariable("permission") String permission,
            @RequestBody ChangePermissionCodeRequest request) {
        var cmd = new ChangePermissionCode(request.code());
        var result = commandBus.send(cmd);

        return result;
    }
}
