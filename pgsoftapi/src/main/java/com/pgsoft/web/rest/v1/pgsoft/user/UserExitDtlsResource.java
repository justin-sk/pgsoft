package com.pgsoft.web.rest.v1.pgsoft.user;

import com.pgsoft.persistence.impl.jpa.dbo.UserExitDtls;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.user.UserExitDtlsService;
import com.pgsoft.service.impl.user.binding.UserExitDtlsBinding;
import com.pgsoft.service.impl.user.dto.UserExitDtlsDTO;
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
@Api(tags = {"User Exit Details"})
@RequestMapping("api/v1/user/exit/detail")
public class UserExitDtlsResource implements IPgSoftResource {
    private final UserExitDtlsService UserExitDtlsService;

    public UserExitDtlsResource(com.pgsoft.service.impl.user.UserExitDtlsService UserExitDtlsService) {
        this.UserExitDtlsService = UserExitDtlsService;
    }

    @GetMapping(value = "/{userId}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<UserExitDtlsDTO> readById(@PathVariable("userId") Long userId) {
        return UserExitDtlsService.read(userId, userId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<UserExitDtlsDTO>> readAll(@QuerydslPredicate(root = UserExitDtls.class, bindings = UserExitDtlsBinding.class) Predicate predicate, Pageable pageable) {
        return UserExitDtlsService.readAll(predicate, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<UserExitDtlsDTO> create(@PathVariable("userId") Long userId, @RequestBody UserExitDtlsDTO newObject) {
        return UserExitDtlsService.create(userId, newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<UserExitDtlsDTO> update(@PathVariable("userId") Long id, @RequestBody UserExitDtlsDTO newObject) {
        return UserExitDtlsService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{userId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("userId") Long id) {
        UserExitDtlsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
