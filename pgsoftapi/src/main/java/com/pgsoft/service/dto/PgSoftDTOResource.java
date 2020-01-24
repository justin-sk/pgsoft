package com.pgsoft.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PgSoftDTOResource<DTO_TYPE extends IPgSoftDTO> extends Resource<DTO_TYPE> implements IPgSoftDTO {
    public PgSoftDTOResource(DTO_TYPE content, Link... links) {
        super(content, links);
    }

    public PgSoftDTOResource(DTO_TYPE content, Iterable<Link> links) {
        super(content, links);
    }

    @JsonProperty("_embedded")
    private final Map<String, IPgSoftDTO> embedded = new HashMap<>();

    public void embedResource(String relationship, IPgSoftDTO dto) {
        embedded.put(relationship, dto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PgSoftDTOResource)) return false;
        if (!super.equals(o)) return false;
        PgSoftDTOResource<?> that = (PgSoftDTOResource<?>) o;
        return Objects.equals(embedded, that.embedded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), embedded);
    }

    public Map<String, IPgSoftDTO> getEmbedded() {
        return this.embedded;
    }
}
