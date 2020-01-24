package com.pgsoft.service.impl.type.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.OccupancyType;
import com.pgsoft.web.rest.v1.pgsoft.type.OccupancyTypeResource;
import org.springframework.stereotype.Component;

@Component
public class OccupancyTypeMapper extends TypeMapperAdapter<OccupancyType> {
    public OccupancyTypeMapper() {
        super(OccupancyTypeResource.class);
    }
}