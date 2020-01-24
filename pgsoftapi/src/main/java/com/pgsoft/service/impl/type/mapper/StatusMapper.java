package com.pgsoft.service.impl.type.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Status;
import com.pgsoft.web.rest.v1.pgsoft.type.StatusResource;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper extends TypeMapperAdapter<Status> {
    public StatusMapper() {
        super(StatusResource.class);
    }
}