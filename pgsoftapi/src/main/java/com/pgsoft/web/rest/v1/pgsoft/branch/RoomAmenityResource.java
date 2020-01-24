package com.pgsoft.web.rest.v1.pgsoft.branch;

import com.pgsoft.persistence.impl.jpa.dbo.RoomAmenity;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.RoomAmenityService;
import com.pgsoft.service.impl.branch.binding.RoomAmenityBinding;
import com.pgsoft.web.rest.v1.IPgSoftResource;
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
@Api(tags = {"Room Amenity"})
@RequestMapping("api/v1/room/amenity")
public class RoomAmenityResource implements IPgSoftResource {
    private final RoomAmenityService roomAmenityService;

    public RoomAmenityResource(RoomAmenityService roomAmenityService) {
        this.roomAmenityService = roomAmenityService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<XRefDTO>> readAll(@QuerydslPredicate(root = RoomAmenity.class, bindings = RoomAmenityBinding.class) Predicate predicate
            , Pageable pageable) {
        return roomAmenityService.readAll(predicate, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<XRefDTO> create(@RequestBody XRefDTO dto) {
        return roomAmenityService.create(dto.getParentId(), dto.getXrefId()).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{roomId}/{amenityId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> delete(@PathVariable("roomId") Long roomId, @PathVariable("amenityId") Long id) {
        roomAmenityService.deleteById(roomId, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
