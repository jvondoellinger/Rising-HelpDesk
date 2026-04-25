package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.mappers.accessprofile.AccessProfileCommandMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.mappers.accessprofile.AccessProfileResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.request.AddAccessProfilePermissionRequest;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.request.ChangeAccessProfileNameRequest;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.request.CreateAccessProfileRequest;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.request.RemoveAccessProfilePermissionRequest;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.AddPermissionsAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile.RemovePermissionsAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos.PermissionsDTO;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.bus.CommandBus;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.queries.accessprofile.FindAccessProfileByIdQuery;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/access-profile")
@AllArgsConstructor
public class AccessProfileController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    private final AccessProfileCommandMapper commandMapper;
    private final AccessProfileResponseMapper responseMapper;

    @GetMapping
    public Result<?> search(@RequestParam UUID id) {
        var query = new FindAccessProfileByIdQuery(id);
        var result = queryBus.send(query);

        return result;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Result<?> create(@RequestBody CreateAccessProfileRequest request) {
        var cmd = commandMapper.from(request);
        var result = commandBus.send(cmd);

        return result;

    }

    @PutMapping("/name")
    public Result<?> changeNameAccessProfile(@RequestBody ChangeAccessProfileNameRequest request) {
        var cmd = commandMapper.from(request);
        var result = commandBus.send(cmd);

        return result;
    }

    @FixAfter
    @PatchMapping("/permissions/remove")
    public Result<?> removePermissionAccessProfile(@RequestBody RemoveAccessProfilePermissionRequest request) {
        var permissions = new PermissionsDTO(request.permissions());
        var cmd = new RemovePermissionsAccessProfileCommand(request.id(), permissions);
        var result = commandBus.send(cmd);
        return result;

    }

    @FixAfter
    @PatchMapping("/permissions/add")
    public Result<?> addPermissionAccessProfile(@RequestBody AddAccessProfilePermissionRequest request) {
        var permissions = new PermissionsDTO(request.permissions());
        var cmd = new AddPermissionsAccessProfileCommand(request.id(), permissions);
        var result = commandBus.send(cmd);

        return result;
    }
}
