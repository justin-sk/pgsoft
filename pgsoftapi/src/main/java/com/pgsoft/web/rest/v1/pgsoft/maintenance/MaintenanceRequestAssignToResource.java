package com.pgsoft.web.rest.v1.pgsoft.maintenance;

import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequestAssignTo;
import com.pgsoft.persistence.impl.jpa.dbo.QMaintenanceRequestAssignTo;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.maintenance.MaintenanceRequestAssignToService;
import com.pgsoft.service.impl.maintenance.binding.MaintenanceRequestAssignToBinding;
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
@Api(tags = {"Maintenance Request Assign To"})
@RequestMapping("api/v1/maintenance/request/{maintenanceRequestId}/assign/to")
public class MaintenanceRequestAssignToResource implements IPgSoftResource {
    private final MaintenanceRequestAssignToService maintenanceRequestAssignToService;

    public MaintenanceRequestAssignToResource(MaintenanceRequestAssignToService maintenanceRequestAssignToService) {
        this.maintenanceRequestAssignToService = maintenanceRequestAssignToService;
    }

    @GetMapping(value = "/{userId}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<XRefDTO> readById(@PathVariable("maintenanceRequestId") Long parentId, @PathVariable("userId") Long id) {
        return maintenanceRequestAssignToService.read(parentId, id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<XRefDTO>> readAll(@QuerydslPredicate(root = MaintenanceRequestAssignTo.class, bindings = MaintenanceRequestAssignToBinding.class) Predicate predicate
            , @PathVariable("maintenanceRequestId") Long maintenanceRequestId
            , Pageable pageable) {
        BooleanBuilder where = new BooleanBuilder();
        QMaintenanceRequestAssignTo qMaintenanceRequestAssignTo = QMaintenanceRequestAssignTo.maintenanceRequestAssignTo;
        where.and(qMaintenanceRequestAssignTo.maintenanceRequest.id.eq(maintenanceRequestId));
        if (predicate != null) {
            where.and(predicate);
        }
        return maintenanceRequestAssignToService.readAll(where, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, maintenanceRequestId, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<XRefDTO> create(@PathVariable("maintenanceRequestId") Long maintenanceRequestId, @RequestBody XRefDTO newObject) {
        return maintenanceRequestAssignToService.create(maintenanceRequestId, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{userId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("maintenanceRequestId") Long maintenanceRequestId, @PathVariable("userId") Long id) {
        maintenanceRequestAssignToService.deleteById(maintenanceRequestId, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
