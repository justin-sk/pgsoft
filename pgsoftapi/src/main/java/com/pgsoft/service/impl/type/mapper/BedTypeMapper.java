package com.pgsoft.service.impl.type.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.BedType;
import com.pgsoft.web.rest.v1.pgsoft.type.BedTypeResource;
import org.springframework.stereotype.Component;

@Component
public class BedTypeMapper extends TypeMapperAdapter<BedType> {
    public BedTypeMapper() {
        super(BedTypeResource.class);
    }
}