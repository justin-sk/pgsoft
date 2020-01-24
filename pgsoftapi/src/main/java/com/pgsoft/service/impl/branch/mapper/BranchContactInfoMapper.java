package com.pgsoft.service.impl.branch.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Branch;
import com.pgsoft.persistence.impl.jpa.dbo.BranchContactInfo;
import com.pgsoft.persistence.impl.jpa.repository.BranchRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.InternalServerException;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.BranchService;
import com.pgsoft.service.impl.branch.dto.BranchContactInfoDTO;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.branch.BranchContactInfoResource;
import com.pgsoft.web.rest.v1.pgsoft.branch.BranchResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class BranchContactInfoMapper extends PgSoftChildMapperAdapter<BranchContactInfo, BranchContactInfoDTO, Branch> {
    private final BranchService branchService;

    protected BranchContactInfoMapper(BranchRepository repository
            , BranchService branchService) {
        super(repository);
        this.branchService = branchService;
    }

    @Override
    public void populateChildDBO(@NotNull BranchContactInfo dbo, @NotNull BranchContactInfoDTO dto) {
        if (dto.getBranchId() == null) {
            throw new InternalServerException("Child id has not be set");
        }
        dbo.setNm(dto.getName());
        dbo.setPhone(dto.getName());
        dbo.setBranch(this.findParent(dto.getBranchId()).orElseThrow(() -> new NotFoundException("Child DBO has not been found")));
    }

    @Override
    protected BranchContactInfoDTO toDTOImpl(@NotNull BranchContactInfo dbo) {
        BranchContactInfoDTO dto = new BranchContactInfoDTO();
        dto.setName(dbo.getNm());
        dto.setPhone(dbo.getPhone());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, BranchContactInfo dbo) {
        links.add(linkTo(methodOn(BranchResource.class).readById(dbo.getBranch().getId())).withRel("branch"));
    }

    @Override
    protected Link getSelfLink(BranchContactInfo dbo) {
        return linkTo(methodOn(BranchContactInfoResource.class).readById(dbo.getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(BranchContactInfo dbo, @NotNull PgSoftDTOResource<BranchContactInfoDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("branch", branchService.getMapper().toDTO(dbo.getBranch()));
    }
}
