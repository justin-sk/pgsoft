package com.pgsoft.web.rest.v1.pgsoft.user;

import com.pgsoft.persistence.impl.jpa.dbo.QUserBranch;
import com.pgsoft.persistence.impl.jpa.dbo.UserBranch;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.user.UserBranchService;
import com.pgsoft.service.impl.user.binding.UserBranchBinding;
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
@Api(tags = {"User Branch"})
@RequestMapping("api/v1/user/{userId}/branch")

public class UserBranchResource implements IPgSoftResource {
    private final UserBranchService UserBranchService;

    public UserBranchResource(com.pgsoft.service.impl.user.UserBranchService UserBranchService) {
        this.UserBranchService = UserBranchService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<XRefDTO>> readAll(@PathVariable("userId") Long userId
            , @QuerydslPredicate(root = UserBranch.class, bindings = UserBranchBinding.class) Predicate predicate
            , Pageable pageable) {
        BooleanBuilder where = new BooleanBuilder();
        QUserBranch qUserBranch = QUserBranch.userBranch;
        where.and(qUserBranch.user.id.eq(userId));
        where.and(predicate);
        return UserBranchService.readAll(where
                , pageable
                , p -> linkTo(methodOn(this.getClass()).readAll(userId, predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/{branchId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<XRefDTO> readById(@PathVariable("userId") Long userId, @PathVariable("branchId") Long id) {
        return UserBranchService.read(userId, id).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{branchId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("userId") Long userId, @PathVariable("branchId") Long id) {
        UserBranchService.deleteById(userId, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/{branchId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<XRefDTO> create(@PathVariable("userId") Long userId, @PathVariable("branchId") Long id) {
        return UserBranchService.create(userId, id).orElseThrow(NotFoundException::new);
    }
}
