package com.pgsoft.web.rest.v1.pgsoft.branch;

import com.pgsoft.persistence.impl.jpa.dbo.BranchInfo;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.BranchInfoService;
import com.pgsoft.service.impl.branch.binding.BranchInfoBinding;
import com.pgsoft.service.impl.branch.dto.BranchInfoDTO;
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
@Api(tags = {"Branch Info"})
@RequestMapping("api/v1/branch/info")
public class BranchInfoResource implements IPgSoftResource {
    private final BranchInfoService branchInfoService;

    public BranchInfoResource(BranchInfoService branchInfoService) {
        this.branchInfoService = branchInfoService;
    }

    @GetMapping(value = "/{branchId}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<BranchInfoDTO> readById(@PathVariable("branchId") Long id) {
        return branchInfoService.readById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<BranchInfoDTO>> readAll(@QuerydslPredicate(root = BranchInfo.class, bindings = BranchInfoBinding.class) Predicate predicate, Pageable pageable) {
        return branchInfoService.readAll(predicate, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, pageable)).withSelfRel())
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<BranchInfoDTO> create(@RequestBody BranchInfoDTO newObject) {
        return branchInfoService.create(newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{branchId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<BranchInfoDTO> update(@PathVariable("branchId") Long id, @RequestBody BranchInfoDTO newObject) {
        return branchInfoService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{branchId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("branchId") Long id) {
        branchInfoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
