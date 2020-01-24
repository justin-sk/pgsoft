package com.pgsoft.service.impl.user.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.dbo.UserRole;
import com.pgsoft.persistence.impl.jpa.repository.UserRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.type.RoleService;
import com.pgsoft.service.impl.user.UserService;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.user.UserResource;
import com.pgsoft.web.rest.v1.pgsoft.user.UserRoleResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserRoleMapper extends PgSoftChildMapperAdapter<UserRole, XRefDTO, User> {
    private final UserService userService;
    private final RoleService roleService;

    public UserRoleMapper(UserService userService, RoleService roleService, UserRepository repository) {
        super(repository);
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void populateChildDBO(@NotNull UserRole dbo, @NotNull XRefDTO dto) {
        dbo.setUser(userService.findById(dto.getParentId()).orElseThrow(NotFoundException::new));
        dbo.setRole(roleService.findById(dto.getXrefId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected XRefDTO toDTOImpl(@NotNull UserRole dbo) {
        return new XRefDTO();
    }

    @Override
    protected void populateLinksImpl(List<Link> links, UserRole dbo) {
        links.add(linkTo(methodOn(UserResource.class).readById(dbo.getUser().getId())).withRel("user"));
    }

    @Override
    protected Link getSelfLink(UserRole dbo) {
        return linkTo(methodOn(UserRoleResource.class).readById(dbo.getUser().getId(), dbo.getRole().getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(UserRole dbo, @NotNull PgSoftDTOResource<XRefDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("user", userService.getMapper().toDTO(dbo.getUser()));
        dto.embedResource("role", roleService.getMapper().toDTO(dbo.getRole()));
    }
}
