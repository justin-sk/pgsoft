package com.pgsoft.web.rest.v1.pgsoft.user;

import com.pgsoft.persistence.impl.jpa.dbo.QUserUpcomingPayment;
import com.pgsoft.persistence.impl.jpa.dbo.UserUpcomingPayment;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.user.UserUpcomingPaymentService;
import com.pgsoft.service.impl.user.binding.UserUpcomingPaymentBinding;
import com.pgsoft.service.impl.user.dto.UserUpcomingPaymentDTO;
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
@Api(tags = {"User Upcoming Payment"})
@RequestMapping("api/v1/user/upcoming/payment")
public class UserUpcomingPaymentResource implements IPgSoftResource {
    private final UserUpcomingPaymentService userUpcomingPaymentService;

    public UserUpcomingPaymentResource(UserUpcomingPaymentService userUpcomingPaymentService) {
        this.userUpcomingPaymentService = userUpcomingPaymentService;
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<UserUpcomingPaymentDTO> readById(@PathVariable("id") Long id) {
        return userUpcomingPaymentService.read(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/{userId}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<UserUpcomingPaymentDTO>> readAll(@PathVariable("userId") Long userId, @QuerydslPredicate(root = UserUpcomingPayment.class, bindings = UserUpcomingPaymentBinding.class) Predicate predicate, Pageable pageable) {
        BooleanBuilder where = new BooleanBuilder();
        QUserUpcomingPayment qUserUpcomingPayment = QUserUpcomingPayment.userUpcomingPayment;
        where.and(qUserUpcomingPayment.user.id.eq(userId));
        if (predicate != null) {
            where.and(predicate);
        }
        return userUpcomingPaymentService.readAll(where, pageable, p -> linkTo(methodOn(this.getClass()).readAll(userId, where, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<UserUpcomingPaymentDTO> create(@PathVariable("userId") Long userId, @RequestBody UserUpcomingPaymentDTO newObject) {
        return userUpcomingPaymentService.create(userId, newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<UserUpcomingPaymentDTO> update(@PathVariable("id") Long id, @RequestBody UserUpcomingPaymentDTO newObject) {
        return userUpcomingPaymentService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        userUpcomingPaymentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
