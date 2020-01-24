package com.pgsoft.service.impl.type.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.SubCategory;
import com.pgsoft.web.rest.v1.pgsoft.type.SubCategoryResource;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryMapper extends TypeMapperAdapter<SubCategory> {
    public SubCategoryMapper() {
        super(SubCategoryResource.class);
    }
}