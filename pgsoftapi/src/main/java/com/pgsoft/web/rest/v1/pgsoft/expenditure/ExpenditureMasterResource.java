package com.pgsoft.web.rest.v1.pgsoft.expenditure;

import com.pgsoft.persistence.impl.jpa.dbo.ExpenditureMaster;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.expenditure.ExpenditureMasterService;
import com.pgsoft.service.impl.expenditure.binding.ExpenditureMasterBinding;
import com.pgsoft.service.impl.expenditure.dto.ExpenditureMasterDTO;
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
@Api(tags = {"Expenditure Master"})
@RequestMapping("api/v1/expenditure/master")
public class ExpenditureMasterResource implements IPgSoftResource {
    private final ExpenditureMasterService expenditureMasterService;

    public ExpenditureMasterResource(ExpenditureMasterService expenditureMasterService) {
        this.expenditureMasterService = expenditureMasterService;
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<ExpenditureMasterDTO> readById(@PathVariable("id") Long id) {
        return expenditureMasterService.readById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<ExpenditureMasterDTO>> readAll(@QuerydslPredicate(root = ExpenditureMaster.class, bindings = ExpenditureMasterBinding.class) Predicate predicate, Pageable pageable) {
        return expenditureMasterService.readAll(predicate, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<ExpenditureMasterDTO> create(@RequestBody ExpenditureMasterDTO newObject) {
        return expenditureMasterService.create(newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<ExpenditureMasterDTO> update(@PathVariable("id") Long id, @RequestBody ExpenditureMasterDTO newObject) {
        return expenditureMasterService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        expenditureMasterService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
