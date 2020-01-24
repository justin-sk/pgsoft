package com.pgsoft.service.impl.menu.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Menu;
import com.pgsoft.service.impl.menu.dto.MenuDTO;
import com.pgsoft.service.mapper.PgSoftParentMapperAdapter;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component
public class MenuMapper extends PgSoftParentMapperAdapter<Menu, MenuDTO> {
    public MenuMapper() {
    }

    @Override
    protected void populateDBO(@NotNull Menu dbo, @NotNull MenuDTO dto) {
        dbo.setCode(dto.getCode());
        dbo.setUrl(dto.getUrl());
    }

    @Override
    @NotNull
    public MenuDTO toDTOImpl(@NotNull Menu dbo) {
        MenuDTO dto = new MenuDTO();
        dto.setCode(dbo.getCode());
        dto.setUrl(dbo.getUrl());
        return dto;
    }

    @Override
    protected void populateLinksImpl(List<Link> links, Menu dbo) {

    }

    @Override
    protected Link getSelfLink(Menu dbo) {
        return null;
    }
}