package com.pgsoft.service.impl.type.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Designation;
import com.pgsoft.web.rest.v1.pgsoft.type.DesignationResource;
import org.springframework.stereotype.Component;

@Component
public class DesignationMapper extends TypeMapperAdapter<Designation> {
    public DesignationMapper() {
        super(DesignationResource.class);
    }
}