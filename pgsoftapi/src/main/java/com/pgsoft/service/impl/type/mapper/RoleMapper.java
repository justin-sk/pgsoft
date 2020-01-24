package com.pgsoft.service.impl.type.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Role;
import com.pgsoft.web.rest.v1.pgsoft.type.RoleResource;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper extends TypeMapperAdapter<Role> {
    public RoleMapper() {
        super(RoleResource.class);
    }
}