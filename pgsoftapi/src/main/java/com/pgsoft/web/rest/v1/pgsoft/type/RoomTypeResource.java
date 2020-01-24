package com.pgsoft.web.rest.v1.pgsoft.type;

import com.pgsoft.persistence.impl.jpa.dbo.RoomType;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.type.RoomTypeService;
import com.pgsoft.service.impl.type.dto.PgSoftTypeDTO;
import com.pgsoft.web.rest.v1.IPgSoftTypeResource;
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
@Api(tags = {"Room Type", "Type"})
@RequestMapping("api/v1/type/room")
public class RoomTypeResource implements IPgSoftTypeResource<PgSoftTypeDTO> {
    private final RoomTypeService RoomTypeService;

    public RoomTypeResource(RoomTypeService RoomTypeService) {
        this.RoomTypeService = RoomTypeService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<PgSoftTypeDTO>> readAll(@QuerydslPredicate(root = RoomType.class) Predicate predicate, Pageable pageable) {
        return RoomTypeService.readAll(pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, p)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PutMapping(path = "/code/{code}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<PgSoftTypeDTO> creadByCode(@PathVariable("code") String typeCode) {
        return RoomTypeService.creadByCode(typeCode).orElseThrow(NotFoundException::new);
    }

    @GetMapping(path = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<PgSoftTypeDTO> readById(@PathVariable("id") Long id) {
        return RoomTypeService.readById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    @GetMapping(path = "/code/{code}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<PgSoftTypeDTO> readByCode(@PathVariable("code") String typeCode) {
        return RoomTypeService.readByCode(typeCode).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<PgSoftTypeDTO> update(@PathVariable("id") Long typeId, @RequestBody PgSoftTypeDTO type) {
        return RoomTypeService.update(typeId, type).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/code/{code}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteByCode(@PathVariable("code") String code) {
        RoomTypeService.deleteByCode(code);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        RoomTypeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
