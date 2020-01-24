package com.pgsoft.service.impl.branch.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Branch;
import com.pgsoft.persistence.impl.jpa.dbo.BranchInfo;
import com.pgsoft.persistence.impl.jpa.repository.BranchRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.BranchService;
import com.pgsoft.service.impl.branch.dto.BranchInfoDTO;
import com.pgsoft.service.impl.type.CityService;
import com.pgsoft.service.impl.type.OccupancyTypeService;
import com.pgsoft.service.impl.type.dto.PgSoftTypeDTO;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.branch.BranchInfoResource;
import com.pgsoft.web.rest.v1.pgsoft.branch.BranchResource;
import com.pgsoft.web.rest.v1.pgsoft.type.CityResource;
import com.pgsoft.web.rest.v1.pgsoft.type.OccupancyTypeResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class BranchInfoMapper extends PgSoftChildMapperAdapter<BranchInfo, BranchInfoDTO, Branch> {
    private final CityService cityService;
    private final OccupancyTypeService occupancyTypeService;
    private final BranchService branchService;

    public BranchInfoMapper(BranchRepository branchRepository
            , CityService cityService
            , OccupancyTypeService occupancyTypeService
            , BranchService branchService) {
        super(branchRepository);
        this.cityService = cityService;
        this.occupancyTypeService = occupancyTypeService;
        this.branchService = branchService;
    }

    @Override
    protected void populateDBO(@NotNull BranchInfo dbo, @NotNull BranchInfoDTO dto) {
        if (dbo.getBranch() == null) {
            dbo.setBranch(branchService.getMapper().toNewDBO(new PgSoftTypeDTO(dto.getCode())));
        } else {
            dbo.getBranch().setCode(dto.getCode());
        }
        dbo.setNm(dto.getName());
        dbo.setNickNm(dto.getNickName());
        dbo.setAddress(dto.getAddress());
        dbo.setSts(dto.isStatus());
        dbo.setCity(this.cityService.findById(dto.getCityId()).orElseThrow(() -> new NotFoundException("City Not Found")));
        dbo.setOccupancyType(this.occupancyTypeService.findById(dto.getOccupancyTypeId()).orElseThrow(() -> new NotFoundException("Occupancy Type Not Found")));
    }

    @Override
    public void populateChildDBO(@NotNull BranchInfo dbo, @NotNull BranchInfoDTO dto) {

    }

    @Override
    protected BranchInfoDTO toDTOImpl(@NotNull BranchInfo dbo) {
        BranchInfoDTO dto = new BranchInfoDTO();
        dto.setName(dbo.getNm());
        dto.setNickName(dbo.getNickNm());
        dto.setAddress(dbo.getAddress());
        dto.setStatus(dbo.getSts());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, BranchInfo dbo) {
        links.add(linkTo(methodOn(CityResource.class).readById(dbo.getCity().getId())).withRel("city"));
        links.add(linkTo(methodOn(OccupancyTypeResource.class).readById(dbo.getOccupancyType().getId())).withRel("occupancyType"));
        links.add(linkTo(methodOn(BranchInfoResource.class).readById(dbo.getId())).withRel("branches"));
    }

    @Override
    protected Link getSelfLink(BranchInfo dbo) {
        return linkTo(methodOn(BranchResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(BranchInfo dbo, @NotNull PgSoftDTOResource<BranchInfoDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("city", cityService.getMapper().toDTO(dbo.getCity()));
        dto.embedResource("occupancyType", occupancyTypeService.getMapper().toDTO(dbo.getOccupancyType()));
        dto.embedResource("branch", branchService.getMapper().toDTO(dbo.getBranch()));
    }
}
