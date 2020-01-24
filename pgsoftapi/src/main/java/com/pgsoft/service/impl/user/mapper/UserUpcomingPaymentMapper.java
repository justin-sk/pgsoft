package com.pgsoft.service.impl.user.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.dbo.UserUpcomingPayment;
import com.pgsoft.persistence.impl.jpa.repository.UserRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.payment.PaymentDtlsService;
import com.pgsoft.service.impl.type.PaymentTypeService;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.impl.user.dto.UserUpcomingPaymentDTO;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.user.UserResource;
import com.pgsoft.web.rest.v1.pgsoft.user.UserUpcomingPaymentResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserUpcomingPaymentMapper extends PgSoftChildMapperAdapter<UserUpcomingPayment, UserUpcomingPaymentDTO, User> {
    private final UserService userService;
    private final PaymentTypeService paymentTypeService;
    private final PaymentDtlsService paymentDtlsService;

    public UserUpcomingPaymentMapper(UserRepository repository
            , UserService userService
            , PaymentTypeService paymentTypeService
            , PaymentDtlsService paymentDtlsService) {
        super(repository);
        this.userService = userService;
        this.paymentTypeService = paymentTypeService;
        this.paymentDtlsService = paymentDtlsService;
    }

    @Override
    public void populateChildDBO(@NotNull UserUpcomingPayment dbo, @NotNull UserUpcomingPaymentDTO dto) {
        dbo.setAmount(dto.getAmount());
        dbo.setDate(dto.getPaymentDate());
        dbo.setSts(dto.getStatus());
        dbo.setUser(userService.findById(dto.getUserId()).orElseThrow(NotFoundException::new));
        dbo.setPaymentType(paymentTypeService.findById(dto.getPaymentTypeId()).orElseThrow(NotFoundException::new));
        dbo.setPaymentDtls(paymentDtlsService.findById(dto.getPaymentDetailsId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected UserUpcomingPaymentDTO toDTOImpl(@NotNull UserUpcomingPayment dbo) {
        UserUpcomingPaymentDTO dto = new UserUpcomingPaymentDTO();
        dto.setStatus(dbo.getSts());
        dto.setAmount(dbo.getAmount());
        dto.setPaymentDate(dbo.getDate());
        dto.setPaymentDetailsId(dbo.getPaymentDtls().getId());
        dto.setPaymentTypeId(dbo.getPaymentType().getId());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, UserUpcomingPayment dbo) {
        links.add(linkTo(methodOn(UserResource.class).readById(dbo.getUser().getId())).withRel("user"));
    }

    @Override
    protected Link getSelfLink(UserUpcomingPayment dbo) {
        return linkTo(methodOn(UserUpcomingPaymentResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(UserUpcomingPayment dbo, @NotNull PgSoftDTOResource<UserUpcomingPaymentDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("user", userService.getMapper().toDTO(dbo.getUser()));
        dto.embedResource("paymentType", paymentTypeService.getMapper().toDTO(dbo.getPaymentType()));
        dto.embedResource("paymentDetails", paymentDtlsService.getMapper().toDTO(dbo.getPaymentDtls()));
    }
}
