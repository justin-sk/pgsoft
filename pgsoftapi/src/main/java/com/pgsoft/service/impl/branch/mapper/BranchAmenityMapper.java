package com.pgsoft.service.impl.branch.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Branch;
import com.pgsoft.persistence.impl.jpa.dbo.BranchAmenity;
import com.pgsoft.persistence.impl.jpa.repository.BranchRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.amenity.AmenityService;
import com.pgsoft.service.impl.branch.BranchService;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.amenity.AmenityResource;
import com.pgsoft.web.rest.v1.pgsoft.branch.BranchAmenityResource;
import com.pgsoft.web.rest.v1.pgsoft.branch.BranchResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class BranchAmenityMapper extends PgSoftChildMapperAdapter<BranchAmenity, XRefDTO, Branch> {
    private final BranchService branchService;
    private final AmenityService amenityService;

    public BranchAmenityMapper(BranchService branchService, AmenityService amenityService, BranchRepository repository) {
        super(repository);
        this.branchService = branchService;
        this.amenityService = amenityService;
    }

    @Override
    public void populateChildDBO(@NotNull BranchAmenity dbo, @NotNull XRefDTO dto) {
        dbo.setBranch(branchService.findById(dto.getParentId()).orElseThrow(NotFoundException::new));
        dbo.setAmenity(amenityService.findById(dto.getXrefId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected XRefDTO toDTOImpl(@NotNull BranchAmenity dbo) {
        return new XRefDTO();
    }

    @Override
    protected void populateLinksImpl(List<Link> links, BranchAmenity dbo) {
        links.add(linkTo(methodOn(BranchAmenityResource.class).readAll(dbo.getBranch().getId(), null, null)).withRel("associatedAmenities"));
        links.add(linkTo(methodOn(BranchResource.class).readById(dbo.getBranch().getId())).withRel("branch"));
        links.add(linkTo(methodOn(AmenityResource.class).readById(dbo.getAmenity().getId())).withRel("amenity"));
    }

    @Override
    protected Link getSelfLink(BranchAmenity dbo) {
        return linkTo(methodOn(BranchAmenityResource.class).readAll(dbo.getId(), null, null)).withSelfRel();
    }

    @Override
    protected void embeddedResources(BranchAmenity dbo, @NotNull PgSoftDTOResource<XRefDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("branch", branchService.getMapper().toDTO(dbo.getBranch()));
        dto.embedResource("amenity", amenityService.getMapper().toDTO(dbo.getAmenity()));
    }
}
