package com.pgsoft.service.impl.type.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.TypeDBO;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.type.dto.PgSoftTypeDTO;
import com.pgsoft.service.mapper.PgSoftParentMapperAdapter;
import com.pgsoft.web.rest.v1.IPgSoftTypeResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Slf4j
public abstract class TypeMapperAdapter<DBO_TYPE extends TypeDBO> extends PgSoftParentMapperAdapter<DBO_TYPE, PgSoftTypeDTO> {
    private final Class<? extends IPgSoftTypeResource> resourceClass;

    public TypeMapperAdapter(Class<? extends IPgSoftTypeResource> resourceClass) {
        this.resourceClass = resourceClass;
    }

    @Override
    protected void populateDBO(@NotNull DBO_TYPE dbo, @NotNull PgSoftTypeDTO dto) {
        dbo.setCode(dto.getCode());
    }

    @NotNull
    @Override
    public PgSoftDTOResource<PgSoftTypeDTO> toDTO(@NotNull DBO_TYPE dbo) {
        final PgSoftDTOResource<PgSoftTypeDTO> pgSoftTypeDTOPgSoftDTOResource = super.toDTO(dbo);
        pgSoftTypeDTOPgSoftDTOResource.getContent().setId(null);
        pgSoftTypeDTOPgSoftDTOResource.getContent().setParentId(null);
        return pgSoftTypeDTOPgSoftDTOResource;
    }

    @Override
    @NotNull
    public PgSoftTypeDTO toDTOImpl(@NotNull DBO_TYPE dbo) {
        final PgSoftTypeDTO pgSoftTypeDTO = new PgSoftTypeDTO();
        pgSoftTypeDTO.setCode(dbo.getCode());
        return pgSoftTypeDTO;
    }

    @Override
    public Link getSelfLink(@NotNull DBO_TYPE dbo) {
        return linkTo(methodOn((Class<? extends IPgSoftTypeResource<PgSoftTypeDTO>>) resourceClass).readById(dbo.getId())).
                withSelfRel();
    }

    @Override
    protected void populateLinksImpl(@NotNull List<Link> links, @NotNull DBO_TYPE dbo) {
        links.add(linkTo(methodOn((Class<? extends IPgSoftTypeResource<PgSoftTypeDTO>>) resourceClass).readAll(null, null)).withRel("siblingTypes"));
        links.add(linkTo(methodOn((Class<? extends IPgSoftTypeResource<PgSoftTypeDTO>>) resourceClass).readByCode(dbo.getCode())).withSelfRel());
    }
}
