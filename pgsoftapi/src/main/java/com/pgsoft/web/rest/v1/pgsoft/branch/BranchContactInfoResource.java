package com.pgsoft.web.rest.v1.pgsoft.branch;

import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.BranchContactInfoService;
import com.pgsoft.service.impl.branch.dto.BranchContactInfoDTO;
import com.pgsoft.web.rest.v1.IPgSoftResource;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Api(tags = {"Branch Contact Info"})
@RequestMapping("api/v1/branch/contact/info")
public class BranchContactInfoResource implements IPgSoftResource {
    private final BranchContactInfoService branchContactInfoService;

    public BranchContactInfoResource(BranchContactInfoService branchContactInfoService) {
        this.branchContactInfoService = branchContactInfoService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<BranchContactInfoDTO> readById(Long id) {
        return branchContactInfoService.readById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/{branchId}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<BranchContactInfoDTO>> readAll(@PathVariable("branchId") Long branchId, Pageable pageable) {
        return branchContactInfoService.readAll(branchId, pageable, (p) -> linkTo(methodOn(this.getClass()).readAll(branchId, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "/{branchId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<BranchContactInfoDTO> create(@PathVariable("branchId") Long branchId, @RequestBody BranchContactInfoDTO newObject) {
        return branchContactInfoService.create(branchId, newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{branchId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<BranchContactInfoDTO> update(@PathVariable("branchId") Long branchId, @RequestBody BranchContactInfoDTO newObject) {
        return branchContactInfoService.update(branchId, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(Long id) {
        branchContactInfoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
