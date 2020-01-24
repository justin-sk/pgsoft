package com.pgsoft.service.impl.maintenance.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequest;
import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequestFee;
import com.pgsoft.persistence.impl.jpa.repository.MaintenanceRequestRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.maintenance.MaintenanceRequestService;
import com.pgsoft.service.impl.maintenance.dto.MaintenanceRequestFeeDTO;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.maintenance.MaintenanceRequestFeeResource;
import com.pgsoft.web.rest.v1.pgsoft.maintenance.MaintenanceRequestResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class MaintenanceRequestFeeMapper extends PgSoftChildMapperAdapter<MaintenanceRequestFee, MaintenanceRequestFeeDTO, MaintenanceRequest> {
    private final MaintenanceRequestService maintenanceRequestService;

    public MaintenanceRequestFeeMapper(MaintenanceRequestService maintenanceRequestService
            , MaintenanceRequestRepository repository) {
        super(repository);
        this.maintenanceRequestService = maintenanceRequestService;
    }

    @Override
    public void populateChildDBO(@NotNull MaintenanceRequestFee dbo, @NotNull MaintenanceRequestFeeDTO dto) {
        dbo.setAmount(dto.getAmount());
        dbo.setMaintenanceRequest(maintenanceRequestService.findById(dto.getMaintenanceRequestId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected MaintenanceRequestFeeDTO toDTOImpl(@NotNull MaintenanceRequestFee dbo) {
        MaintenanceRequestFeeDTO dto = new MaintenanceRequestFeeDTO();
        dto.setAmount(dbo.getAmount());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, MaintenanceRequestFee dbo) {
        links.add(linkTo(methodOn(MaintenanceRequestResource.class).readById(dbo.getMaintenanceRequest().getId())).withRel("maintenanceRequest"));
    }

    @Override
    protected Link getSelfLink(MaintenanceRequestFee dbo) {
        return linkTo(methodOn(MaintenanceRequestFeeResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(MaintenanceRequestFee dbo, @NotNull PgSoftDTOResource<MaintenanceRequestFeeDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("maintenanceRequest", maintenanceRequestService.getMapper().toDTO(dbo.getMaintenanceRequest()));
    }
}
