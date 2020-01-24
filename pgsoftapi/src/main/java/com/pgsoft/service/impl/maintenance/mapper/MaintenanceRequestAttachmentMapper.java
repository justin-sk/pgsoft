package com.pgsoft.service.impl.maintenance.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequest;
import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequestAttachment;
import com.pgsoft.persistence.impl.jpa.repository.MaintenanceRequestRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.maintenance.MaintenanceRequestService;
import com.pgsoft.service.impl.maintenance.dto.MaintenanceRequestAttachmentDTO;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.maintenance.MaintenanceRequestAttachmentResource;
import com.pgsoft.web.rest.v1.pgsoft.maintenance.MaintenanceRequestResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class MaintenanceRequestAttachmentMapper extends PgSoftChildMapperAdapter<MaintenanceRequestAttachment, MaintenanceRequestAttachmentDTO, MaintenanceRequest> {
    private final MaintenanceRequestService maintenanceRequestService;

    public MaintenanceRequestAttachmentMapper(MaintenanceRequestService maintenanceRequestService
            , MaintenanceRequestRepository repository) {
        super(repository);
        this.maintenanceRequestService = maintenanceRequestService;
    }

    @Override
    public void populateChildDBO(@NotNull MaintenanceRequestAttachment dbo, @NotNull MaintenanceRequestAttachmentDTO dto) {
        dbo.setAttachmentUrl(dto.getAttachmentURL());
        dbo.setMaintenanceRequest(maintenanceRequestService.findById(dto.getMaintenanceRequestId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected MaintenanceRequestAttachmentDTO toDTOImpl(@NotNull MaintenanceRequestAttachment dbo) {
        MaintenanceRequestAttachmentDTO dto = new MaintenanceRequestAttachmentDTO();
        dto.setAttachmentURL(dbo.getAttachmentUrl());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, MaintenanceRequestAttachment dbo) {
        links.add(linkTo(methodOn(MaintenanceRequestResource.class).readById(dbo.getMaintenanceRequest().getId())).withRel("maintenanceRequest"));
    }

    @Override
    protected Link getSelfLink(MaintenanceRequestAttachment dbo) {
        return linkTo(methodOn(MaintenanceRequestAttachmentResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(MaintenanceRequestAttachment dbo, @NotNull PgSoftDTOResource<MaintenanceRequestAttachmentDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("maintenanceRequest", maintenanceRequestService.getMapper().toDTO(dbo.getMaintenanceRequest()));
    }
}
