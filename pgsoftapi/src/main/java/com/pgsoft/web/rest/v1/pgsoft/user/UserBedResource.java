package com.pgsoft.web.rest.v1.pgsoft.user;

import com.pgsoft.persistence.impl.jpa.dbo.QUserBed;
import com.pgsoft.persistence.impl.jpa.dbo.UserBed;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.user.UserBedService;
import com.pgsoft.service.impl.user.binding.UserBedBinding;
import com.pgsoft.web.rest.v1.IPgSoftResource;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Api(tags = {"User Bed"})
@RequestMapping("api/v1/user/{userId}/bed")
public class UserBedResource implements IPgSoftResource {
    private final UserBedService userBedService;

    public UserBedResource(UserBedService userBedService) {
        this.userBedService = userBedService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<XRefDTO>> readAll(@PathVariable("userId") Long userId
            , @QuerydslPredicate(root = UserBed.class, bindings = UserBedBinding.class) Predicate predicate
            , Pageable pageable) {
        BooleanBuilder where = new BooleanBuilder();
        QUserBed qUserBed = QUserBed.userBed;
        where.and(qUserBed.user.id.eq(userId));
        where.and(predicate);
        return userBedService.readAll(where
                , pageable
                , p -> linkTo(methodOn(this.getClass()).readAll(userId, predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/{bedId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<XRefDTO> readById(@PathVariable("userId") Long userId, @PathVariable("bedId") Long id) {
        return userBedService.read(userId, id).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{bedId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("userId") Long userId, @PathVariable("bedId") Long id) {
        userBedService.deleteById(userId, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/{bedId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<XRefDTO> create(@PathVariable("userId") Long userId, @PathVariable("bedId") Long id) {
        return userBedService.create(userId, id).orElseThrow(NotFoundException::new);
    }
}
