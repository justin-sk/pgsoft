package com.pgsoft.web.rest.v1.pgsoft.menu;

import com.pgsoft.persistence.impl.jpa.dbo.QRoleMenuXref;
import com.pgsoft.persistence.impl.jpa.dbo.RoleMenuXref;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.menu.RoleMenuService;
import com.pgsoft.service.impl.menu.binding.RoleMenuBinding;
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
@Api(tags = {"Role Menu"})
@RequestMapping("api/v1/role/{roleId}/menu")
public class RoleMenuResource implements IPgSoftResource {
    private final RoleMenuService roleMenuService;

    public RoleMenuResource(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<XRefDTO>> readAll(@PathVariable("roleId") Long roleId
            , @QuerydslPredicate(root = RoleMenuXref.class, bindings = RoleMenuBinding.class) Predicate predicate
            , Pageable pageable) {
        BooleanBuilder where = new BooleanBuilder();
        QRoleMenuXref qCategorySubCategoryReference = QRoleMenuXref.roleMenuXref;
        where.and(qCategorySubCategoryReference.role.id.eq(roleId));
        where.and(predicate);
        return roleMenuService.readAll(where
                , pageable
                , p -> linkTo(methodOn(this.getClass()).readAll(roleId, predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{menuId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("roleId") Long roleId, @PathVariable("menuId") Long id) {
        roleMenuService.deleteById(roleId, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/{menuId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<XRefDTO> create(@PathVariable("roleId") Long roleId, @PathVariable("menuId") Long id) {
        return roleMenuService.create(roleId, id).orElseThrow(NotFoundException::new);
    }
}
