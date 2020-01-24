package com.pgsoft.web.rest.v1.pgsoft.payment;

import com.pgsoft.persistence.impl.jpa.dbo.PaymentResponseDtls;
import com.pgsoft.persistence.impl.jpa.dbo.QPaymentResponseDtls;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.payment.PaymentResponseDtlsService;
import com.pgsoft.service.impl.payment.binding.PaymentResponseDtlsBinding;
import com.pgsoft.service.impl.payment.dto.PaymentResponseDtlsDTO;
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
@Api(tags = {"Payment Response Details"})
@RequestMapping("api/v1/payment/{paymentDetailId}/response/details")
public class PaymentResponseDtlsResource implements IPgSoftResource {
    private final PaymentResponseDtlsService paymentResponseDtlsService;

    public PaymentResponseDtlsResource(PaymentResponseDtlsService paymentResponseDtlsService) {
        this.paymentResponseDtlsService = paymentResponseDtlsService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<PaymentResponseDtlsDTO>> readAll(@QuerydslPredicate(root = PaymentResponseDtls.class, bindings = PaymentResponseDtlsBinding.class) Predicate predicate, @PathVariable("paymentDetailId") Long id, Pageable pageable) {
        BooleanBuilder where = new BooleanBuilder();
        QPaymentResponseDtls qPaymentResponseDtls = QPaymentResponseDtls.paymentResponseDtls;
        where.and(qPaymentResponseDtls.paymentDtls.id.eq(id));
        if (predicate != null) {
            where.and(predicate);
        }
        return paymentResponseDtlsService.readAll(where, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, id, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<PaymentResponseDtlsDTO> create(@PathVariable("paymentDetailId") Long id, @RequestBody PaymentResponseDtlsDTO newObject) {
        return paymentResponseDtlsService.create(id, newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<PaymentResponseDtlsDTO> update(@PathVariable("paymentDetailId") Long id, @RequestBody PaymentResponseDtlsDTO newObject) {
        return paymentResponseDtlsService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("paymentDetailId") Long id) {
        paymentResponseDtlsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
