package com.pgsoft.web.rest.v1.pgsoft.expenditure;

import com.pgsoft.persistence.impl.jpa.dbo.ExpenditureAttachments;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.expenditure.ExpenditureAttachmentsService;
import com.pgsoft.service.impl.expenditure.binding.ExpenditureAttachmentsBinding;
import com.pgsoft.service.impl.expenditure.dto.ExpenditureAttachmentsDTO;
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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Api(tags = {"Expenditure Attachment"})
@RequestMapping("api/v1/expenditure/attachment")
public class ExpenditureAttachmentsResource implements IPgSoftResource {
    private final ExpenditureAttachmentsService expenditureAttachmentsService;

    public ExpenditureAttachmentsResource(ExpenditureAttachmentsService expenditureAttachmentsService) {
        this.expenditureAttachmentsService = expenditureAttachmentsService;
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<ExpenditureAttachmentsDTO> readById(@PathVariable("id") Long id) {
        return expenditureAttachmentsService.readById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<ExpenditureAttachmentsDTO>> readAll(@QuerydslPredicate(root = ExpenditureAttachments.class, bindings = ExpenditureAttachmentsBinding.class) Predicate predicate, Pageable pageable) {
        return expenditureAttachmentsService.readAll(predicate, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/{expenditureId}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<ExpenditureAttachmentsDTO>> readAll(@Param(value = "expenditureId") Long expenditureId, Pageable pageable) {
        return expenditureAttachmentsService.readAll(expenditureId, pageable, p -> linkTo(methodOn(this.getClass()).readAll(expenditureId, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "/{expenditureId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<ExpenditureAttachmentsDTO> create(@Param(value = "expenditureId") Long expenditureId, @RequestBody ExpenditureAttachmentsDTO newObject) {
        return expenditureAttachmentsService.create(expenditureId, newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<ExpenditureAttachmentsDTO> update(@PathVariable("id") Long id, @RequestBody ExpenditureAttachmentsDTO newObject) {
        return expenditureAttachmentsService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        expenditureAttachmentsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
