package com.pgsoft.web.rest.v1.pgsoft.payment;

import com.pgsoft.persistence.impl.jpa.dbo.PaymentDtls;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.SummaryDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.payment.PaymentDtlsService;
import com.pgsoft.service.impl.payment.binding.PaymentDtlsBinding;
import com.pgsoft.service.impl.payment.dto.PaymentDtlsDTO;
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
@Api(tags = {"Payment Details"})
@RequestMapping("api/v1/payment/detail")
public class PaymentDtlsResource implements IPgSoftResource {
    private final PaymentDtlsService paymentDtlsService;

    public PaymentDtlsResource(PaymentDtlsService paymentDtlsService) {
        this.paymentDtlsService = paymentDtlsService;
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<PaymentDtlsDTO> readById(@PathVariable("id") Long id) {
        return paymentDtlsService.readById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<PaymentDtlsDTO>> readAll(@QuerydslPredicate(root = PaymentDtls.class, bindings = PaymentDtlsBinding.class) Predicate predicate, Pageable pageable) {
        return paymentDtlsService.readAll(predicate, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/summary", produces = MediaTypes.HAL_JSON_VALUE)
    public Collection<SummaryDTO> readSummary(@QuerydslPredicate(root = PaymentDtls.class, bindings = PaymentDtlsBinding.class) Predicate predicate) {
        return paymentDtlsService.readSummary(predicate).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/revenueReport", produces = MediaTypes.HAL_JSON_VALUE)
    public Collection<SummaryDTO> revenueReport(@QuerydslPredicate(root = PaymentDtls.class, bindings = PaymentDtlsBinding.class) Predicate predicate) {
        return paymentDtlsService.revenueReport(predicate).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/incomeReport", produces = MediaTypes.HAL_JSON_VALUE)
    public Collection<SummaryDTO> incomeReport(@QuerydslPredicate(root = PaymentDtls.class, bindings = PaymentDtlsBinding.class) Predicate predicate) {
        return paymentDtlsService.incomeReport(predicate).orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<PaymentDtlsDTO> create(@RequestBody PaymentDtlsDTO newObject) {
        return paymentDtlsService.create(newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<PaymentDtlsDTO> update(@PathVariable("id") Long id, @RequestBody PaymentDtlsDTO newObject) {
        return paymentDtlsService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        paymentDtlsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
