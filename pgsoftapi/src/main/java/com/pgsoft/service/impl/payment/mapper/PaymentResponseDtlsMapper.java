package com.pgsoft.service.impl.payment.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.PaymentDtls;
import com.pgsoft.persistence.impl.jpa.dbo.PaymentResponseDtls;
import com.pgsoft.persistence.impl.jpa.repository.PaymentDtlsRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.payment.PaymentDtlsService;
import com.pgsoft.service.impl.payment.dto.PaymentResponseDtlsDTO;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.payment.PaymentDtlsResource;
import com.pgsoft.web.rest.v1.pgsoft.payment.PaymentResponseDtlsResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PaymentResponseDtlsMapper extends PgSoftChildMapperAdapter<PaymentResponseDtls, PaymentResponseDtlsDTO, PaymentDtls> {
    private final PaymentDtlsService paymentDtlsService;

    public PaymentResponseDtlsMapper(PaymentDtlsService paymentDtlsService
            , PaymentDtlsRepository repository) {
        super(repository);
        this.paymentDtlsService = paymentDtlsService;
    }

    @Override
    public void populateChildDBO(@NotNull PaymentResponseDtls dbo, @NotNull PaymentResponseDtlsDTO dto) {
        dbo.setMsg(dto.getMessage());
        dbo.setReferenceNo(dto.getReferenceNumber());
        dbo.setRspCd(dto.getResponseCode());
        dbo.setVendorId(dto.getVendorId());
        dbo.setPaymentDtls(paymentDtlsService.findById(dto.getPaymentDetailsId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected PaymentResponseDtlsDTO toDTOImpl(@NotNull PaymentResponseDtls dbo) {
        PaymentResponseDtlsDTO dto = new PaymentResponseDtlsDTO();
        dto.setMessage(dbo.getMsg());
        dto.setReferenceNumber(dbo.getReferenceNo());
        dto.setResponseCode(dbo.getRspCd());
        dto.setVendorId(dbo.getVendorId());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, PaymentResponseDtls dbo) {
        links.add(linkTo(methodOn(PaymentDtlsResource.class).readById(dbo.getPaymentDtls().getId())).withRel("paymentDetails"));
    }

    @Override
    protected Link getSelfLink(PaymentResponseDtls dbo) {
        return linkTo(methodOn(PaymentResponseDtlsResource.class).readAll(null, dbo.getPaymentDtls().getId(), null)).withSelfRel();
    }

    @Override
    protected void embeddedResources(PaymentResponseDtls dbo, @NotNull PgSoftDTOResource<PaymentResponseDtlsDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("paymentDetails", paymentDtlsService.getMapper().toDTO(dbo.getPaymentDtls()));
    }
}
