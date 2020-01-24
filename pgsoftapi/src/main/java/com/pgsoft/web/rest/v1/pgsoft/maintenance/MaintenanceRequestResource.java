package com.pgsoft.web.rest.v1.pgsoft.maintenance;

import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequest;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.SummaryDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.maintenance.MaintenanceRequestService;
import com.pgsoft.service.impl.maintenance.binding.MaintenanceRequestBinding;
import com.pgsoft.service.impl.maintenance.dto.MaintenanceRequestDTO;
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

import java.util.Collection;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Api(tags = {"Maintenance Request"})
@RequestMapping("api/v1/maintenance/request")
public class MaintenanceRequestResource implements IPgSoftResource {
    private final MaintenanceRequestService maintenanceRequestService;

    public MaintenanceRequestResource(MaintenanceRequestService maintenanceRequestService) {
        this.maintenanceRequestService = maintenanceRequestService;
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<MaintenanceRequestDTO> readById(@PathVariable("id") Long id) {
        return maintenanceRequestService.readById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<MaintenanceRequestDTO>> readAll(@QuerydslPredicate(root = MaintenanceRequest.class, bindings = MaintenanceRequestBinding.class) Predicate predicate, Pageable pageable) {
        return maintenanceRequestService.readAll(predicate, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/summary", produces = MediaTypes.HAL_JSON_VALUE)
    public Collection<SummaryDTO> readSummary(@QuerydslPredicate(root = MaintenanceRequest.class, bindings = MaintenanceRequestBinding.class) Predicate predicate) {
        return maintenanceRequestService.readSummary(predicate).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/monthlySummary", produces = MediaTypes.HAL_JSON_VALUE)
    public Collection<SummaryDTO> monthlySummary(@QuerydslPredicate(root = MaintenanceRequest.class, bindings = MaintenanceRequestBinding.class) Predicate predicate) {
        return maintenanceRequestService.monthlySummary(predicate).orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<MaintenanceRequestDTO> create(@RequestBody MaintenanceRequestDTO newObject) {
        return maintenanceRequestService.create(newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<MaintenanceRequestDTO> update(@PathVariable("id") Long id, @RequestBody MaintenanceRequestDTO newObject) {
        return maintenanceRequestService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        maintenanceRequestService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
