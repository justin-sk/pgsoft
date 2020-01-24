package com.pgsoft.web.rest.v1.pgsoft.branch;

import com.pgsoft.persistence.impl.jpa.dbo.Branch;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.BranchService;
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
@Api(tags = {"Branch"})
@RequestMapping("api/v1/branch")
public class BranchResource implements IPgSoftTypeResource<PgSoftTypeDTO> {
    private final BranchService branchService;

    public BranchResource(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<PgSoftTypeDTO>> readAll(@QuerydslPredicate(root = Branch.class) Predicate predicate, Pageable pageable) {
        return branchService.readAll(pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, p)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PutMapping(path = "/code/{branchCode}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<PgSoftTypeDTO> creadByCode(@PathVariable("branchCode") String typeCode) {
        return branchService.creadByCode(typeCode).orElseThrow(NotFoundException::new);
    }

    @GetMapping(path = "/{branchId}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<PgSoftTypeDTO> readById(@PathVariable("branchId") Long branchId) {
        return branchService.readById(branchId).orElseThrow(NotFoundException::new);
    }

    @Override
    @GetMapping(path = "/code/{branchCode}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<PgSoftTypeDTO> readByCode(@PathVariable("branchCode") String typeCode) {
        return branchService.readByCode(typeCode).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{branchId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<PgSoftTypeDTO> update(@PathVariable("branchId") Long typeId, @RequestBody PgSoftTypeDTO branchType) {
        return branchService.update(typeId, branchType).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/code/{branchCode}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteByCode(@PathVariable("branchCode") String branchCode) {
        branchService.deleteByCode(branchCode);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{branchId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("branchId") Long branchId) {
        branchService.deleteById(branchId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
