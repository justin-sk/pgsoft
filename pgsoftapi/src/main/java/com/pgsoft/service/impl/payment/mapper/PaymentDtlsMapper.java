package com.pgsoft.service.impl.payment.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.PaymentDtls;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.payment.dto.PaymentDtlsDTO;
import com.pgsoft.service.impl.type.PaymentTypeService;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.mapper.PgSoftParentMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.payment.PaymentDtlsResource;
import com.pgsoft.web.rest.v1.pgsoft.type.PaymentTypeResource;
import com.pgsoft.web.rest.v1.pgsoft.user.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PaymentDtlsMapper extends PgSoftParentMapperAdapter<PaymentDtls, PaymentDtlsDTO> {
    private final PaymentTypeService paymentTypeService;
    private final UserService userService;

    public PaymentDtlsMapper(PaymentTypeService paymentTypeService
            , UserService userService) {
        this.paymentTypeService = paymentTypeService;
        this.userService = userService;
    }

    @Override
    protected void populateDBO(@NotNull PaymentDtls dbo, @NotNull PaymentDtlsDTO dto) {
        dbo.setAmount(dto.getAmount());
        dbo.setPaymentDt(dto.getPaymentDate());
        dbo.setSts(dto.getStatus());
        dbo.setPaymentType(paymentTypeService.findById(dto.getPaymentTypeId()).orElseThrow(NotFoundException::new));
        dbo.setUser(userService.findById(dto.getUserId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected PaymentDtlsDTO toDTOImpl(@NotNull PaymentDtls dbo) {
        PaymentDtlsDTO dto = new PaymentDtlsDTO();
        dto.setAmount(dbo.getAmount());
        dto.setPaymentDate(dbo.getPaymentDt());
        dto.setStatus(dbo.getSts());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, PaymentDtls dbo) {
        links.add(linkTo(methodOn(PaymentTypeResource.class).readById(dbo.getPaymentType().getId())).withRel("paymentType"));
        links.add(linkTo(methodOn(UserResource.class).readById(dbo.getUser().getId())).withRel("user"));
    }

    @Override
    protected Link getSelfLink(PaymentDtls dbo) {
        return linkTo(methodOn(PaymentDtlsResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(PaymentDtls dbo, @NotNull PgSoftDTOResource<PaymentDtlsDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("user", userService.getMapper().toDTO(dbo.getUser()));
        dto.embedResource("paymentType", paymentTypeService.getMapper().toDTO(dbo.getPaymentType()));
    }
}
