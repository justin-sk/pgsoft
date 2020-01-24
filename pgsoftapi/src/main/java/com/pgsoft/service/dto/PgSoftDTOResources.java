package com.pgsoft.service.dto;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;

import java.util.ArrayList;

public class PgSoftDTOResources<DTO_TYPE extends IPgSoftDTO> extends Resources<DTO_TYPE> implements IPgSoftDTO {
    public PgSoftDTOResources(Iterable<DTO_TYPE> content) {
        super(content, new ArrayList<>());
    }

    public PgSoftDTOResources(Iterable<DTO_TYPE> content, Iterable<Link> links) {
        super(content, links);
    }
}
