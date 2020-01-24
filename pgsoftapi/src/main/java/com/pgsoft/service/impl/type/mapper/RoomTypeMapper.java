package com.pgsoft.service.impl.type.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.RoomType;
import com.pgsoft.web.rest.v1.pgsoft.type.RoomTypeResource;
import org.springframework.stereotype.Component;

@Component
public class RoomTypeMapper extends TypeMapperAdapter<RoomType> {
    public RoomTypeMapper() {
        super(RoomTypeResource.class);
    }
}