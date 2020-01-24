package com.pgsoft.service.impl.type.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Category;
import com.pgsoft.web.rest.v1.pgsoft.type.CategoryResource;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends TypeMapperAdapter<Category> {
    public CategoryMapper() {
        super(CategoryResource.class);
    }
}
