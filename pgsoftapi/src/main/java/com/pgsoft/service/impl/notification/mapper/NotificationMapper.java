package com.pgsoft.service.impl.notification.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Notification;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.BranchService;
import com.pgsoft.service.impl.notification.dto.NotificationDTO;
import com.pgsoft.service.impl.type.RecurrenceTypeService;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.mapper.PgSoftParentMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.branch.BranchResource;
import com.pgsoft.web.rest.v1.pgsoft.notification.NotificationResource;
import com.pgsoft.web.rest.v1.pgsoft.type.RecurrenceTypeResource;
import com.pgsoft.web.rest.v1.pgsoft.user.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class NotificationMapper extends PgSoftParentMapperAdapter<Notification, NotificationDTO> {
    private final RecurrenceTypeService recurrenceTypeService;
    private final BranchService branchService;
    private final UserService userService;

    public NotificationMapper(RecurrenceTypeService recurrenceTypeService
            , BranchService branchService
            , UserService userService) {
        this.recurrenceTypeService = recurrenceTypeService;
        this.branchService = branchService;
        this.userService = userService;
    }

    @Override
    protected void populateDBO(@NotNull Notification dbo, @NotNull NotificationDTO dto) {
        dbo.setHdr(dto.getHeader());
        dbo.setDesc(dto.getDescription());
        dbo.setRecurrenceType(recurrenceTypeService.findById(dto.getRecurrenceTypeId()).orElseThrow(NotFoundException::new));
        dbo.setBranch(branchService.findById(dto.getBranchId()).orElseThrow(NotFoundException::new));
        dbo.setUser(userService.findById(dto.getUserId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected NotificationDTO toDTOImpl(@NotNull Notification dbo) {
        NotificationDTO dto = new NotificationDTO();
        dto.setHeader(dbo.getHdr());
        dto.setDescription(dbo.getDesc());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, Notification dbo) {
        links.add(linkTo(methodOn(RecurrenceTypeResource.class).readById(dbo.getRecurrenceType().getId())).withRel("recurrenceType"));
        links.add(linkTo(methodOn(BranchResource.class).readById(dbo.getBranch().getId())).withRel("branch"));
        links.add(linkTo(methodOn(UserResource.class).readById(dbo.getUser().getId())).withRel("user"));
    }

    @Override
    protected Link getSelfLink(Notification dbo) {
        return linkTo(methodOn(NotificationResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(Notification dbo, @NotNull PgSoftDTOResource<NotificationDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("user", userService.getMapper().toDTO(dbo.getUser()));
        dto.embedResource("branch", branchService.getMapper().toDTO(dbo.getBranch()));
        dto.embedResource("recurrenceType", recurrenceTypeService.getMapper().toDTO(dbo.getRecurrenceType()));
    }
}
