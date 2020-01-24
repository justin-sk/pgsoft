package com.pgsoft.service.impl.user.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.dbo.UserBranch;
import com.pgsoft.persistence.impl.jpa.repository.UserRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.branch.BranchService;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.user.UserBranchResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserBranchMapper extends PgSoftChildMapperAdapter<UserBranch, XRefDTO, User> {
    private final UserService userService;
    private final BranchService branchService;

    public UserBranchMapper(UserRepository repository
            , UserService userService
            , BranchService branchService) {
        super(repository);
        this.userService = userService;
        this.branchService = branchService;
    }

    @Override
    public void populateChildDBO(@NotNull UserBranch dbo, @NotNull XRefDTO dto) {
        dbo.setUser(userService.findById(dto.getParentId()).orElseThrow(NotFoundException::new));
        dbo.setBranch(branchService.findById(dto.getXrefId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected XRefDTO toDTOImpl(@NotNull UserBranch dbo) {
        return new XRefDTO();
    }

    @Override
    protected void populateLinksImpl(List<Link> links, UserBranch dbo) {

    }

    @Override
    protected Link getSelfLink(UserBranch dbo) {
        return linkTo(methodOn(UserBranchResource.class).readById(dbo.getUser().getId(), dbo.getBranch().getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(UserBranch dbo, @NotNull PgSoftDTOResource<XRefDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("user", userService.getMapper().toDTO(dbo.getUser()));
        dto.embedResource("branch", branchService.getMapper().toDTO(dbo.getBranch()));
    }
}
