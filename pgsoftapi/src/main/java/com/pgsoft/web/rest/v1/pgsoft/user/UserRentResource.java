package com.pgsoft.web.rest.v1.pgsoft.user;

import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.user.UserRentService;
import com.pgsoft.service.impl.user.dto.UserRentDTO;
import com.pgsoft.web.rest.v1.IPgSoftResource;
import io.swagger.annotations.Api;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"User Rent"})
@RequestMapping("api/v1/user/{userId}/rent")
public class UserRentResource implements IPgSoftResource {
    private final UserRentService UserRentService;

    public UserRentResource(com.pgsoft.service.impl.user.UserRentService UserRentService) {
        this.UserRentService = UserRentService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<UserRentDTO> readById(@PathVariable("userId") Long userId) {
        return UserRentService.read(userId, userId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<UserRentDTO> create(@PathVariable("userId") Long userId, @RequestBody UserRentDTO newObject) {
        return UserRentService.create(userId, newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<UserRentDTO> update(@PathVariable("userId") Long userId, @RequestBody UserRentDTO newObject) {
        return UserRentService.update(userId, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("userId") Long userId) {
        UserRentService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
