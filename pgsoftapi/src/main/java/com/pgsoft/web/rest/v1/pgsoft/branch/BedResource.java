package com.pgsoft.web.rest.v1.pgsoft.branch;

import com.pgsoft.persistence.impl.jpa.dbo.Bed;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.SummaryDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.BedService;
import com.pgsoft.service.impl.branch.binding.BedBinding;
import com.pgsoft.service.impl.branch.dto.BedDTO;
import com.pgsoft.web.rest.v1.IPgSoftResource;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Api(tags = {"Bed"})
@RequestMapping("api/v1/bed")
public class BedResource implements IPgSoftResource {
    private final BedService bedService;

    public BedResource(BedService bedService) {
        this.bedService = bedService;
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<BedDTO> readById(@Param(value = "id") Long id) {
        return bedService.readById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<BedDTO>> readAll(@QuerydslPredicate(root = Bed.class, bindings = BedBinding.class) Predicate predicate
            , Pageable pageable) {
        return bedService.readAll(predicate, pageable, (p) -> linkTo(methodOn(this.getClass()).readAll(predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/summary", produces = MediaTypes.HAL_JSON_VALUE)
    public Collection<SummaryDTO> readSummary(@QuerydslPredicate(root = Bed.class, bindings = BedBinding.class) Predicate predicate) {
        return bedService.readSummary(predicate).orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<BedDTO> create(@RequestBody BedDTO newObject) {
        return bedService.create(newObject.getRoomId(), newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<BedDTO> update(@RequestBody BedDTO updatedObject) {
        return bedService.update(updatedObject.getId(), updatedObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(Long id) {
        bedService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
