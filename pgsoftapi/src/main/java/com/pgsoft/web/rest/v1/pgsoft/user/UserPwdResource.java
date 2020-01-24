package com.pgsoft.web.rest.v1.pgsoft.user;

import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.user.UserPwdService;
import com.pgsoft.service.impl.user.dto.UserPwdDTO;
import com.pgsoft.web.rest.v1.IPgSoftResource;
import io.swagger.annotations.Api;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"User Password"})
@RequestMapping("api/v1/user/pwd")
public class UserPwdResource implements IPgSoftResource {
    private final UserPwdService UserPwdService;

    public UserPwdResource(com.pgsoft.service.impl.user.UserPwdService UserPwdService) {
        this.UserPwdService = UserPwdService;
    }

    @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<UserPwdDTO> create(@PathVariable("userId") Long userId, @RequestBody UserPwdDTO newObject) {
        return UserPwdService.create(userId, newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<UserPwdDTO> update(@PathVariable("userId") Long id, @RequestBody UserPwdDTO newObject) {
        return UserPwdService.update(id, newObject).orElseThrow(NotFoundException::new);
    }
}
