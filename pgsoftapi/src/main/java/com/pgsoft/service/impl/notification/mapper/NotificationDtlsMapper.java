package com.pgsoft.service.impl.notification.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Notification;
import com.pgsoft.persistence.impl.jpa.dbo.NotificationDtls;
import com.pgsoft.persistence.impl.jpa.repository.NotificationRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.notification.NotificationService;
import com.pgsoft.service.impl.notification.dto.NotificationDtlsDTO;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.notification.NotificationDtlsResource;
import com.pgsoft.web.rest.v1.pgsoft.notification.NotificationResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class NotificationDtlsMapper extends PgSoftChildMapperAdapter<NotificationDtls, NotificationDtlsDTO, Notification> {
    private final NotificationService notificationService;

    public NotificationDtlsMapper(NotificationService notificationService
            , NotificationRepository repository) {
        super(repository);
        this.notificationService = notificationService;
    }

    @Override
    public void populateChildDBO(@NotNull NotificationDtls dbo, @NotNull NotificationDtlsDTO dto) {
        dbo.setSentSts(dto.getNotificationSent());
        dbo.setDt(dto.getSentDate());
        dbo.setNotification(notificationService.findById(dto.getNotificationId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected NotificationDtlsDTO toDTOImpl(@NotNull NotificationDtls dbo) {
        NotificationDtlsDTO dto = new NotificationDtlsDTO();
        dto.setNotificationSent(dbo.getSentSts());
        dto.setSentDate(dbo.getDt());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, NotificationDtls dbo) {
        links.add(linkTo(methodOn(NotificationResource.class).readById(dbo.getNotification().getId())).withRel("notification"));
    }

    @Override
    protected Link getSelfLink(NotificationDtls dbo) {
        return linkTo(methodOn(NotificationDtlsResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(NotificationDtls dbo, @NotNull PgSoftDTOResource<NotificationDtlsDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("notification", notificationService.getMapper().toDTO(dbo.getNotification()));
    }
}
