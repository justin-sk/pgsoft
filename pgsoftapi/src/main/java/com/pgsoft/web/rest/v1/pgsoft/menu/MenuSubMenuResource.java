package com.pgsoft.web.rest.v1.pgsoft.menu;

import com.pgsoft.persistence.impl.jpa.dbo.MenuSubMenuReference;
import com.pgsoft.persistence.impl.jpa.dbo.QMenuSubMenuReference;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.menu.MenuSubMenuService;
import com.pgsoft.service.impl.menu.binding.MenuSubMenuBinding;
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
@Api(tags = {"Menu Sub-Menu"})
@RequestMapping("api/v1/menu/{menuId}/submenu")
public class MenuSubMenuResource implements IPgSoftResource {
    private final MenuSubMenuService menuSubMenuService;

    public MenuSubMenuResource(MenuSubMenuService menuSubMenuService) {
        this.menuSubMenuService = menuSubMenuService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<XRefDTO>> readAll(@PathVariable("menuId") Long menuId
            , @QuerydslPredicate(root = MenuSubMenuReference.class, bindings = MenuSubMenuBinding.class) Predicate predicate
            , Pageable pageable) {
        BooleanBuilder where = new BooleanBuilder();
        QMenuSubMenuReference qMenuSubMenuReference = QMenuSubMenuReference.menuSubMenuReference;
        where.and(qMenuSubMenuReference.menu.id.eq(menuId));
        where.and(predicate);
        return menuSubMenuService.readAll(where
                , pageable
                , p -> linkTo(methodOn(this.getClass()).readAll(menuId, predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/{subMenuId}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<XRefDTO> readyById(@PathVariable("menuId") Long menuId, @PathVariable("subMenuId") Long id) {
        return menuSubMenuService.read(menuId, id).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{subMenuId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("menuId") Long menuId, @PathVariable("subMenuId") Long id) {
        menuSubMenuService.deleteById(menuId, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/{subMenuId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<XRefDTO> create(@PathVariable("menuId") Long menuId, @PathVariable("subMenuId") Long id) {
        return menuSubMenuService.create(menuId, id).orElseThrow(NotFoundException::new);
    }
}
