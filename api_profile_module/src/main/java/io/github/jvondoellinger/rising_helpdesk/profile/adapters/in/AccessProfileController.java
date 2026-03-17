package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.mappers.AccessProfileResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.request.AddAccessProfilePermissionRequest;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.request.ChangeAccessProfileNameRequest;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.request.CreateAccessProfileRequest;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.request.RemoveAccessProfilePermissionRequest;
import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.accessprofile.AddPermissionsAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.accessprofile.ChangeNameAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.accessprofile.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.accessprofile.RemovePermissionsAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.PermissionsDTO;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.accessprofile.AddPermissionsAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.accessprofile.ChangeNameAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.accessprofile.CreateAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.commands.accessprofile.RemovePermissionsAccessProfileHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.handlers.query.FindAccessProfileByIdQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.FindAccessProfileByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/access-profile")
@AllArgsConstructor
public class AccessProfileController {
    private final CreateAccessProfileHandler accessProfileUseCase;
    private final FindAccessProfileByIdQueryHandler findAccessProfileByIdQueryHandler;
    private final ChangeNameAccessProfileHandler changeNameAccessProfileHandler;
    private final RemovePermissionsAccessProfileHandler removePermissionsAccessProfileHandler;
    private final AddPermissionsAccessProfileHandler addAccessProfileNamePermissionRequest;

    private final AccessProfileResponseMapper responseMapper;

    @GetMapping
    public ResponseEntity<?> search(@RequestParam UUID id) {
        var query = new FindAccessProfileByIdQuery(id);
        var result = findAccessProfileByIdQueryHandler.handle(query);

        return result.fold(
                onSuccess -> ResponseEntity.ok(responseMapper.from(onSuccess.value())),
                onFailure -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(onFailure.error().getMessage())
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody CreateAccessProfileRequest request) {
        var permissions = request.permissions().stream().map(Permission::of).toList();
        var cmd = new CreateAccessProfileCommand(request.name(), permissions);

        return accessProfileUseCase.handle(cmd)
                .fold(
                        success -> ResponseEntity.accepted().build(),
                        failure -> ResponseEntity.badRequest().body(failure.error().getMessage())
                );
    }

    @PutMapping("/name")
    public ResponseEntity<?> changeNameAccessProfile(@RequestBody ChangeAccessProfileNameRequest request) {
        var cmd = new ChangeNameAccessProfileCommand(request.id(), request.name());

        return changeNameAccessProfileHandler.handle(cmd)
                .fold(
                        success -> ResponseEntity.accepted().build(),
                        failure -> ResponseEntity.badRequest().body(failure.error().getMessage())
                );

    }

    @PatchMapping("/permissions/remove")
    public ResponseEntity<?> removePermissionAccessProfile(@RequestBody RemoveAccessProfilePermissionRequest request) {
        var permissions = new PermissionsDTO(request.permissions());
        var cmd = new RemovePermissionsAccessProfileCommand(request.id(), permissions);

        return removePermissionsAccessProfileHandler.handle(cmd)
                .fold(
                        success -> ResponseEntity.accepted().build(),
                        failure -> ResponseEntity.badRequest().body(failure.error().getMessage())
                );
    }

    @PatchMapping("/permissions/add")
    public ResponseEntity<?> addPermissionAccessProfile(@RequestBody AddAccessProfilePermissionRequest request) {
        var permissions = new PermissionsDTO(request.permissions());
        var cmd = new AddPermissionsAccessProfileCommand(request.id(), permissions);

        return addAccessProfileNamePermissionRequest.handle(cmd)
                .fold(
                        success -> ResponseEntity.accepted().build(),
                        failure -> ResponseEntity.badRequest().body(failure.error().getMessage())
                );

    }
}
