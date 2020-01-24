package com.pgsoft.service.impl.type.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.City;
import com.pgsoft.web.rest.v1.pgsoft.type.CityResource;
import org.springframework.stereotype.Component;

@Component
public class CityMapper extends TypeMapperAdapter<City> {
    public CityMapper() {
        super(CityResource.class);
    }
}