package com.pgsoft.service.impl.menu.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Menu;
import com.pgsoft.persistence.impl.jpa.dbo.MenuSubMenuReference;
import com.pgsoft.persistence.impl.jpa.repository.MenuRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.menu.MenuService;
import com.pgsoft.service.impl.type.SubMenuService;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.menu.MenuSubMenuResource;
import com.pgsoft.web.rest.v1.pgsoft.type.MenuResource;
import com.pgsoft.web.rest.v1.pgsoft.type.SubMenuResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class MenuSubMenuMapper extends PgSoftChildMapperAdapter<MenuSubMenuReference, XRefDTO, Menu> {
    private final MenuService menuService;
    private final SubMenuService subMenuService;

    public MenuSubMenuMapper(SubMenuService subMenuService
            , MenuService menuService
            , MenuRepository repository) {
        super(repository);
        this.menuService = menuService;
        this.subMenuService = subMenuService;
    }

    @Override
    public void populateChildDBO(@NotNull MenuSubMenuReference dbo, @NotNull XRefDTO dto) {
        dbo.setSubMenu(subMenuService.findById(dto.getXrefId()).orElseThrow(NotFoundException::new));
        dbo.setMenu(menuService.findById(dto.getParentId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected XRefDTO toDTOImpl(@NotNull MenuSubMenuReference dbo) {
        return new XRefDTO();
    }

    @Override
    protected void populateLinksImpl(List<Link> links, MenuSubMenuReference dbo) {
        links.add(linkTo(methodOn(MenuResource.class).readById(dbo.getMenu().getId())).withRel("menu"));
        links.add(linkTo(methodOn(SubMenuResource.class).readById(dbo.getSubMenu().getId())).withRel("subMenu"));
    }

    @Override
    protected Link getSelfLink(MenuSubMenuReference dbo) {
        return linkTo(methodOn(MenuSubMenuResource.class).readyById(dbo.getMenu().getId(), dbo.getSubMenu().getId())).withSelfRel();
    }

    @Override
    protected void embeddedResources(MenuSubMenuReference dbo, @NotNull PgSoftDTOResource<XRefDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("menu", menuService.getMapper().toDTO(dbo.getMenu()));
        dto.embedResource("subMenu", subMenuService.getMapper().toDTO(dbo.getSubMenu()));
    }
}
