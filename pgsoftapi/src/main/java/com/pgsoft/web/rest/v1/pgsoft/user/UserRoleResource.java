package com.pgsoft.web.rest.v1.pgsoft.user;

import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.user.UserRoleService;
import com.pgsoft.web.rest.v1.IPgSoftResource;
import io.swagger.annotations.Api;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"User Role"})
@RequestMapping("api/v1/user/{userId}/role")
public class UserRoleResource implements IPgSoftResource {
    private final UserRoleService UserRoleService;

    public UserRoleResource(com.pgsoft.service.impl.user.UserRoleService UserRoleService) {
        this.UserRoleService = UserRoleService;
    }

    @GetMapping(value = "/{roleId}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<XRefDTO> readById(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) {
        return UserRoleService.read(userId, roleId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<XRefDTO> create(@PathVariable("userId") Long userId, @RequestBody XRefDTO newObject) {
        return UserRoleService.create(userId, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{roleId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) {
        UserRoleService.delete(userId, roleId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
