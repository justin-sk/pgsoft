package com.pgsoft.service.impl.type.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.ExpenditureCategory;
import com.pgsoft.web.rest.v1.pgsoft.type.ExpenditureCategoryResource;
import org.springframework.stereotype.Component;

@Component
public class ExpenditureCategoryMapper extends TypeMapperAdapter<ExpenditureCategory> {
    public ExpenditureCategoryMapper() {
        super(ExpenditureCategoryResource.class);
    }
}