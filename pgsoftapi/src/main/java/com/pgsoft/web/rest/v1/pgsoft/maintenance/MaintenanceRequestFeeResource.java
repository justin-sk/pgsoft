package com.pgsoft.web.rest.v1.pgsoft.maintenance;

import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequestFee;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.maintenance.MaintenanceRequestFeeService;
import com.pgsoft.service.impl.maintenance.binding.MaintenanceRequestFeeBinding;
import com.pgsoft.service.impl.maintenance.dto.MaintenanceRequestFeeDTO;
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
@Api(tags = {"Maintenance Request Fee"})
@RequestMapping("api/v1/maintenance/request")
public class MaintenanceRequestFeeResource implements IPgSoftResource {
    private final MaintenanceRequestFeeService maintenanceRequestFeeService;

    public MaintenanceRequestFeeResource(MaintenanceRequestFeeService maintenanceRequestFeeService) {
        this.maintenanceRequestFeeService = maintenanceRequestFeeService;
    }

    @GetMapping(value = "/{maintenanceRequestId}/fee", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<MaintenanceRequestFeeDTO> readById(@PathVariable("maintenanceRequestId") Long id) {
        return maintenanceRequestFeeService.readById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/fee", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<MaintenanceRequestFeeDTO>> readAll(@QuerydslPredicate(root = MaintenanceRequestFee.class, bindings = MaintenanceRequestFeeBinding.class) Predicate predicate, Pageable pageable) {
        return maintenanceRequestFeeService.readAll(predicate, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "/{maintenanceRequestId}/fee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<MaintenanceRequestFeeDTO> create(@PathVariable("maintenanceRequestId") Long id, @RequestBody MaintenanceRequestFeeDTO newObject) {
        return maintenanceRequestFeeService.create(id, newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{maintenanceRequestId}/fee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<MaintenanceRequestFeeDTO> update(@PathVariable("maintenanceRequestId") Long id, @RequestBody MaintenanceRequestFeeDTO newObject) {
        return maintenanceRequestFeeService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{maintenanceRequestId}/fee", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("maintenanceRequestId") Long id) {
        maintenanceRequestFeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
