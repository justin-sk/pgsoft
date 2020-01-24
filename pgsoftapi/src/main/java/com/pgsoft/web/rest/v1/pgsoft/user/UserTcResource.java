package com.pgsoft.web.rest.v1.pgsoft.user;

import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.user.UserTcService;
import com.pgsoft.service.impl.user.dto.UserTcDTO;
import com.pgsoft.web.rest.v1.IPgSoftResource;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class UserTcResource implements IPgSoftResource {
    private final UserTcService userTcService;

    public UserTcResource(UserTcService userTcService) {
        this.userTcService = userTcService;
    }

    @GetMapping(value = "/{userId}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<UserTcDTO> readById(@PathVariable("userId") Long userId) {
        return userTcService.read(userId, userId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<UserTcDTO> create(@PathVariable("userId") Long userId, @RequestBody UserTcDTO newObject) {
        return userTcService.create(userId, newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<UserTcDTO> update(@PathVariable("userId") Long id, @RequestBody UserTcDTO newObject) {
        return userTcService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{userId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("userId") Long id) {
        userTcService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
