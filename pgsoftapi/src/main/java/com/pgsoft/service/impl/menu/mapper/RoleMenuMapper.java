package com.pgsoft.service.impl.menu.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Role;
import com.pgsoft.persistence.impl.jpa.dbo.RoleMenuXref;
import com.pgsoft.persistence.impl.jpa.repository.RoleRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.menu.MenuService;
import com.pgsoft.service.impl.type.RoleService;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.menu.RoleMenuResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class RoleMenuMapper extends PgSoftChildMapperAdapter<RoleMenuXref, XRefDTO, Role> {
    private final RoleService roleService;
    private final MenuService menuService;

    public RoleMenuMapper(RoleService roleService
            , MenuService menuService
            , RoleRepository repository) {
        super(repository);
        this.menuService = menuService;
        this.roleService = roleService;
    }

    @Override
    public void populateChildDBO(@NotNull RoleMenuXref dbo, @NotNull XRefDTO dto) {
        dbo.setMenu(menuService.findById(dto.getXrefId()).orElseThrow(NotFoundException::new));
        dbo.setRole(roleService.findById(dto.getParentId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected XRefDTO toDTOImpl(@NotNull RoleMenuXref dbo) {
        return new XRefDTO();
    }

    @Override
    protected void populateLinksImpl(List<Link> links, RoleMenuXref dbo) {

    }

    @Override
    protected Link getSelfLink(RoleMenuXref dbo) {
        return linkTo(methodOn(RoleMenuResource.class).readAll(dbo.getRole().getId(), null, null)).withSelfRel();
    }

    @Override
    protected void embeddedResources(RoleMenuXref dbo, @NotNull PgSoftDTOResource<XRefDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("role", roleService.getMapper().toDTO(dbo.getRole()));
        dto.embedResource("menu", menuService.getMapper().toDTO(dbo.getMenu()));
    }
}
