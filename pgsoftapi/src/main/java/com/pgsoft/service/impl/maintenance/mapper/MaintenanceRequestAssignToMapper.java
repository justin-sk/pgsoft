package com.pgsoft.service.impl.maintenance.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequest;
import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequestAssignTo;
import com.pgsoft.persistence.impl.jpa.repository.MaintenanceRequestRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.maintenance.MaintenanceRequestService;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.maintenance.MaintenanceRequestAssignToResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class MaintenanceRequestAssignToMapper extends PgSoftChildMapperAdapter<MaintenanceRequestAssignTo, XRefDTO, MaintenanceRequest> {
    private final MaintenanceRequestService maintenanceRequestService;
    private final UserService userService;

    public MaintenanceRequestAssignToMapper(MaintenanceRequestService maintenanceRequestService
            , UserService userService
            , MaintenanceRequestRepository repository) {
        super(repository);
        this.userService = userService;
        this.maintenanceRequestService = maintenanceRequestService;
    }

    @Override
    public void populateChildDBO(@NotNull MaintenanceRequestAssignTo dbo, @NotNull XRefDTO dto) {
        dbo.setMaintenanceRequest(maintenanceRequestService.findById(dto.getParentId()).orElseThrow(NotFoundException::new));
        dbo.setUser(userService.findById(dto.getXrefId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected XRefDTO toDTOImpl(@NotNull MaintenanceRequestAssignTo dbo) {
        return new XRefDTO();
    }

    @Override
    protected void populateLinksImpl(List<Link> links, MaintenanceRequestAssignTo dbo) {

    }

    @Override
    protected Link getSelfLink(MaintenanceRequestAssignTo dbo) {
        return linkTo(methodOn(MaintenanceRequestAssignToResource.class).readById(dbo.getMaintenanceRequest().getId(), dbo.getUser().getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(MaintenanceRequestAssignTo dbo, @NotNull PgSoftDTOResource<XRefDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("maintenanceRequest", maintenanceRequestService.getMapper().toDTO(dbo.getMaintenanceRequest()));
        dto.embedResource("user", userService.getMapper().toDTO(dbo.getUser()));
    }
}
