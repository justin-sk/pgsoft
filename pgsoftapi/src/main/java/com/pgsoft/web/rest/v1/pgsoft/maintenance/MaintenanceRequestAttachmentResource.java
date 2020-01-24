package com.pgsoft.web.rest.v1.pgsoft.maintenance;

import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequestAttachment;
import com.pgsoft.persistence.impl.jpa.dbo.QMaintenanceRequestAttachment;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.maintenance.MaintenanceRequestAttachmentService;
import com.pgsoft.service.impl.maintenance.binding.MaintenanceRequestAttachmentBinding;
import com.pgsoft.service.impl.maintenance.dto.MaintenanceRequestAttachmentDTO;
import com.pgsoft.web.rest.v1.IPgSoftResource;
import com.querydsl.core.BooleanBuilder;
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
@Api(tags = {"Maintenance Request Attachment"})
@RequestMapping("api/v1/maintenance/request/{maintenanceRequestId}/attachment")
public class MaintenanceRequestAttachmentResource implements IPgSoftResource {
    private final MaintenanceRequestAttachmentService maintenanceRequestAttachmentService;

    public MaintenanceRequestAttachmentResource(MaintenanceRequestAttachmentService maintenanceRequestAttachmentService) {
        this.maintenanceRequestAttachmentService = maintenanceRequestAttachmentService;
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<MaintenanceRequestAttachmentDTO> readById(@PathVariable("id") Long id) {
        return maintenanceRequestAttachmentService.read(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<MaintenanceRequestAttachmentDTO>> readAll(@QuerydslPredicate(root = MaintenanceRequestAttachment.class, bindings = MaintenanceRequestAttachmentBinding.class) Predicate predicate
            , @PathVariable("maintenanceRequestId") Long maintenanceRequestId
            , Pageable pageable) {
        BooleanBuilder where = new BooleanBuilder();
        QMaintenanceRequestAttachment qMaintenanceRequestAttachment = QMaintenanceRequestAttachment.maintenanceRequestAttachment;
        where.and(qMaintenanceRequestAttachment.maintenanceRequest.id.eq(maintenanceRequestId));
        if (predicate != null) {
            where.and(predicate);
        }
        return maintenanceRequestAttachmentService.readAll(where, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, maintenanceRequestId, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<MaintenanceRequestAttachmentDTO> create(@PathVariable("maintenanceRequestId") Long id, @RequestBody MaintenanceRequestAttachmentDTO newObject) {
        return maintenanceRequestAttachmentService.create(id, newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<MaintenanceRequestAttachmentDTO> update(@PathVariable("id") Long id, @RequestBody MaintenanceRequestAttachmentDTO newObject) {
        return maintenanceRequestAttachmentService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        maintenanceRequestAttachmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
