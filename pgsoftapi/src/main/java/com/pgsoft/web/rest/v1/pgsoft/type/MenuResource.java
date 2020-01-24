package com.pgsoft.web.rest.v1.pgsoft.type;

import com.pgsoft.persistence.impl.jpa.dbo.Menu;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.menu.MenuService;
import com.pgsoft.service.impl.menu.dto.MenuDTO;
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
//@SwaggerDefinition
@Api(tags = {"Menu", "Type"})
@RequestMapping("api/v1/type/menu")
public class MenuResource implements IPgSoftResource {
    private final MenuService menuService;

    public MenuResource(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<MenuDTO>> readAll(@QuerydslPredicate(root = Menu.class) Predicate predicate, Pageable pageable) {
        return menuService.readAll(pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, p)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PutMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<MenuDTO> create(@RequestBody MenuDTO dto) {
        return menuService.create(dto).orElseThrow(NotFoundException::new);
    }

    @GetMapping(path = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<MenuDTO> readById(@PathVariable("id") Long id) {
        return menuService.readById(id).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<MenuDTO> update(@PathVariable("id") Long typeId, @RequestBody MenuDTO type) {
        return menuService.update(typeId, type).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        menuService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

