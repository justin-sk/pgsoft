package com.pgsoft.service.impl.type.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.RecurrenceType;
import com.pgsoft.web.rest.v1.pgsoft.type.RecurrenceTypeResource;
import org.springframework.stereotype.Component;

@Component
public class RecurrenceTypeMapper extends TypeMapperAdapter<RecurrenceType> {
    public RecurrenceTypeMapper() {
        super(RecurrenceTypeResource.class);
    }
}