package com.pgsoft.web.rest.v1.pgsoft.type;

import com.pgsoft.persistence.impl.jpa.dbo.BedType;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.type.BedTypeService;
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
@Api(tags = {"BedType", "Type"})
@RequestMapping("api/v1/type/bed/type")
public class BedTypeResource implements IPgSoftTypeResource<PgSoftTypeDTO> {
    private final BedTypeService bedTypeService;

    public BedTypeResource(BedTypeService bedTypeService) {
        this.bedTypeService = bedTypeService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<PgSoftTypeDTO>> readAll(@QuerydslPredicate(root = BedType.class) Predicate predicate, Pageable pageable) {
        return bedTypeService.readAll(pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, p)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PutMapping(path = "/code/{bedTypeCode}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<PgSoftTypeDTO> creadByCode(@PathVariable("bedTypeCode") String typeCode) {
        return bedTypeService.creadByCode(typeCode).orElseThrow(NotFoundException::new);
    }

    @GetMapping(path = "/{bedTypeId}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<PgSoftTypeDTO> readById(@PathVariable("bedTypeId") Long bedTypeId) {
        return bedTypeService.readById(bedTypeId).orElseThrow(NotFoundException::new);
    }

    @Override
    @GetMapping(path = "/code/{bedTypeCode}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<PgSoftTypeDTO> readByCode(@PathVariable("bedTypeCode") String typeCode) {
        return bedTypeService.readByCode(typeCode).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{bedTypeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<PgSoftTypeDTO> update(@PathVariable("bedTypeId") Long typeId, @RequestBody PgSoftTypeDTO bedType) {
        return bedTypeService.update(typeId, bedType).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/code/{bedTypeCode}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteByCode(@PathVariable("bedTypeCode") String bedTypeCode) {
        bedTypeService.deleteByCode(bedTypeCode);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{bedTypeId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("bedTypeId") Long bedTypeId) {
        bedTypeService.deleteById(bedTypeId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
