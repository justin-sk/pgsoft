package com.pgsoft.web.rest.v1.pgsoft.branch;

import com.pgsoft.persistence.impl.jpa.dbo.BranchAmenity;
import com.pgsoft.persistence.impl.jpa.dbo.QBranchAmenity;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.BranchAmenityService;
import com.pgsoft.service.impl.branch.binding.BranchAmenityBinding;
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
@Api(tags = {"Branch Amenity"})
@RequestMapping("api/v1/branch/{branchId}/amenity")
public class BranchAmenityResource implements IPgSoftResource {
    private final BranchAmenityService branchAmenityService;

    public BranchAmenityResource(BranchAmenityService branchAmenityService) {
        this.branchAmenityService = branchAmenityService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<XRefDTO>> readAll(@PathVariable("branchId") Long branchId
            , @QuerydslPredicate(root = BranchAmenity.class, bindings = BranchAmenityBinding.class) Predicate predicate
            , Pageable pageable) {
        BooleanBuilder where = new BooleanBuilder();
        QBranchAmenity qBranchAmenity = QBranchAmenity.branchAmenity;
        where.and(qBranchAmenity.branch.id.eq(branchId));
        where.and(predicate);
        return branchAmenityService.readAll(where
                , pageable, p -> linkTo(methodOn(this.getClass()).readAll(branchId, predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "/{amenityId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<XRefDTO> create(@PathVariable("branchId") Long branchId, @PathVariable("amenityId") Long id) {
        return branchAmenityService.create(branchId, id).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{amenityId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("branchId") Long branchId, @PathVariable("amenityId") Long id) {
        branchAmenityService.deleteById(branchId, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
