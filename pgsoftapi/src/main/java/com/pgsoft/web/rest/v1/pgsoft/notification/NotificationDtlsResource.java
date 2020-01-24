package com.pgsoft.web.rest.v1.pgsoft.notification;

import com.pgsoft.persistence.impl.jpa.dbo.NotificationDtls;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.notification.NotificationDtlsService;
import com.pgsoft.service.impl.notification.binding.NotificationDtlsBinding;
import com.pgsoft.service.impl.notification.dto.NotificationDtlsDTO;
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
@Api(tags = {"Notification Details"})
@RequestMapping("api/v1/notification/detail")
public class NotificationDtlsResource implements IPgSoftResource {
    private final NotificationDtlsService NotificationDtlsService;

    public NotificationDtlsResource(NotificationDtlsService NotificationDtlsService) {
        this.NotificationDtlsService = NotificationDtlsService;
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<NotificationDtlsDTO> readById(@PathVariable("id") Long id) {
        return NotificationDtlsService.read(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<NotificationDtlsDTO>> readAll(@QuerydslPredicate(root = NotificationDtls.class, bindings = NotificationDtlsBinding.class) Predicate predicate, Pageable pageable) {
        return NotificationDtlsService.readAll(predicate, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<NotificationDtlsDTO> create(@RequestBody NotificationDtlsDTO newObject) {
        return NotificationDtlsService.create(newObject.getNotificationId(), newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<NotificationDtlsDTO> update(@PathVariable("id") Long id, @RequestBody NotificationDtlsDTO newObject) {
        return NotificationDtlsService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        NotificationDtlsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
