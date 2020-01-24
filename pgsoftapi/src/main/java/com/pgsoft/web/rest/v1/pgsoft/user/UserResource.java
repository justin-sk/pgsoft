package com.pgsoft.web.rest.v1.pgsoft.user;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.impl.user.binding.UserBinding;
import com.pgsoft.service.impl.user.dto.UserDTO;
import com.pgsoft.web.rest.v1.IPgSoftResource;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Api(tags = {"User"})
@RequestMapping("api/v1/user")
public class UserResource implements IPgSoftResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{userId}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<UserDTO> readById(@PathVariable("userId") Long userId) {
        return userService.readById(userId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<UserDTO>> readAll(@QuerydslPredicate(root = User.class, bindings = UserBinding.class) Predicate predicate, Pageable pageable) {
        return userService.readAll(predicate, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<UserDTO> create(@RequestBody UserDTO newObject) {
        return userService.create(newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<UserDTO> update(@PathVariable("userId") Long id, @RequestBody UserDTO newObject) {
        return userService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{userId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("userId") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
