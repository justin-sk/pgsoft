package com.pgsoft.web.rest.v1.pgsoft.amenity;

import com.pgsoft.persistence.impl.jpa.dbo.Amenity;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.amenity.AmenityService;
import com.pgsoft.service.impl.amenity.binding.AmenityBinding;
import com.pgsoft.service.impl.amenity.dto.AmenityDTO;
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
@Api(tags = {"Amenity"})
@RequestMapping("api/v1/amenity")
public class AmenityResource implements IPgSoftResource {
    private final AmenityService amenityService;

    public AmenityResource(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    @GetMapping(value = "/{amenityId}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<AmenityDTO> readById(@PathVariable("amenityId") Long id) {
        return amenityService.readById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<AmenityDTO>> readAll(@QuerydslPredicate(root = Amenity.class, bindings = AmenityBinding.class) Predicate predicate, Pageable pageable) {
        return amenityService.readAll(predicate, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{amenityId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("amenityId") Long id) {
        amenityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<AmenityDTO> create(@RequestBody AmenityDTO newObject) {
        return amenityService.create(newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{amenityId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<AmenityDTO> update(@PathVariable("amenityId") Long id, AmenityDTO updatedObject) {
        return amenityService.update(id, updatedObject).orElseThrow(NotFoundException::new);
    }
}
