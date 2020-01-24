package com.pgsoft.service.impl.type.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.SubMenu;
import com.pgsoft.web.rest.v1.pgsoft.type.SubMenuResource;
import org.springframework.stereotype.Component;

@Component
public class SubMenuMapper extends TypeMapperAdapter<SubMenu> {
    public SubMenuMapper() {
        super(SubMenuResource.class);
    }
}