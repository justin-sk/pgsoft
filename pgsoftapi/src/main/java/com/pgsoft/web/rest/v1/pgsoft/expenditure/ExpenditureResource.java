package com.pgsoft.web.rest.v1.pgsoft.expenditure;

import com.pgsoft.persistence.impl.jpa.dbo.Expenditure;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.expenditure.ExpenditureService;
import com.pgsoft.service.impl.expenditure.binding.ExpenditureBinding;
import com.pgsoft.service.impl.expenditure.dto.ExpenditureDTO;
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
@Api(tags = {"Expenditure"})
@RequestMapping("api/v1/expenditure")
public class ExpenditureResource implements IPgSoftResource {
    private final ExpenditureService ExpenditureService;

    public ExpenditureResource(ExpenditureService ExpenditureService) {
        this.ExpenditureService = ExpenditureService;
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<ExpenditureDTO> readById(@PathVariable("id") Long id) {
        return ExpenditureService.readById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<ExpenditureDTO>> readAll(@QuerydslPredicate(root = Expenditure.class, bindings = ExpenditureBinding.class) Predicate predicate, Pageable pageable) {
        return ExpenditureService.readAll(predicate, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<ExpenditureDTO> create(@RequestBody ExpenditureDTO newObject) {
        return ExpenditureService.create(newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<ExpenditureDTO> update(@PathVariable("id") Long id, @RequestBody ExpenditureDTO newObject) {
        return ExpenditureService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        ExpenditureService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
