package com.pgsoft.service.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.IPgSoftDBO;
import com.pgsoft.service.dto.*;
import com.pgsoft.service.exception.InternalServerException;
import com.pgsoft.service.exception.NotFoundException;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.hateoas.Link;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class PgSoftMapper<DBO_TYPE extends IPgSoftDBO, DTO_TYPE extends IPgSoftDTO> {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PgSoftMapper.class);

    protected abstract void populateDBO(@NotNull DBO_TYPE dbo, @NotNull DTO_TYPE dto);

    @NotNull
    protected abstract DTO_TYPE toDTOImpl(@NotNull DBO_TYPE dbo);

    protected abstract void populateLinksImpl(List<Link> links, DBO_TYPE dbo);

    protected abstract Link getSelfLink(DBO_TYPE dbo);

    protected void populateLinks(List<Link> links, DBO_TYPE dbo) {
        final Link selfLink = this.getSelfLink(dbo);
        if (selfLink != null) {
            links.add(selfLink);
        }
        this.populateLinksImpl(links, dbo);
    }

    @NotNull
    private List<Link> getLinks(@NotNull DBO_TYPE dbo) {
        final List<Link> links = new ArrayList<>();
        this.populateLinks(links, dbo);
        return links;
    }

    protected void embeddedResources(DBO_TYPE dbo, @NotNull PgSoftDTOResource<DTO_TYPE> dto) {
        dto.embedResource("metadata", new PgSoftDTOResource<>(this.addEmbeddedMetadata(dbo)));
    }

    @NotNull
    public PgSoftDTOResource<DTO_TYPE> toDTO(@NotNull DBO_TYPE dbo) {
        final PgSoftDTOResource<DTO_TYPE> dto = new PgSoftDTOResource<>(toDTOImpl(dbo));
        dto.add(this.getLinks(dbo));
        this.embeddedResources(dbo, dto);
        return dto;
    }

    @NotNull
    public PgSoftDTOResource<DTO_TYPE> toDTO(@NotNull Revision<Integer, DBO_TYPE> revision) {
        @NotNull final PgSoftDTOResource<DTO_TYPE> dto = this.toDTO(revision.getEntity());
        @NotNull final MetadataDTO metadataDTO = this.addEmbeddedMetadata(revision.getEntity());
        metadataDTO.getMetadata().put("revisionNumber", revision.getMetadata().getRequiredRevisionNumber());
        metadataDTO.getMetadata().put("modifiedDate", revision.getMetadata().getRequiredRevisionInstant());
        dto.embedResource("metadata", new PgSoftDTOResource<>(metadataDTO));
        return dto;
    }


    @NotNull
    protected MetadataDTO addEmbeddedMetadata(@NotNull DBO_TYPE dbo) {
        final MetadataDTO metadataDTO = new MetadataDTO();
        metadataDTO.getMetadata().put("id", String.valueOf(dbo.getId()));
        return metadataDTO;
    }

    public Class<DBO_TYPE> getDBOClass() {
        final Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            return (Class<DBO_TYPE>) Class.forName(type.getTypeName());
        } catch (ClassNotFoundException e) {
            log.error("Unable to get DBO type from mapper", e);
            throw new InternalServerException("Unable to get DBO type from mapper");
        }
    }

    @NotNull
    public DBO_TYPE toNewDBO(@NotNull DTO_TYPE dto) {
        final DBO_TYPE newDBOInstance = this.getNewDBOInstance();
        this.populateDBO(newDBOInstance, dto);
        return newDBOInstance;
    }

    @NotNull
    public DBO_TYPE getNewDBOInstance() {
        try {
            return getDBOClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Unable to instantiate class from mapper", e);
            throw new InternalServerException("Unable to instantiate class from mapper");
        }
    }

    @NotNull
    @Size(min = 1)
    public PgSoftDTOResources<PgSoftDTOResource<DTO_TYPE>> toDTOs(@NotNull @Size(min = 1) List<DBO_TYPE> dbos) {
        final List<PgSoftDTOResource<DTO_TYPE>> collect = dbos.stream().map(this::toDTO).collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new NotFoundException();
        }
        return new PgSoftDTOResources<>(collect);
    }


    @NotNull
    @Size(min = 1)
    public PgSoftDTOPagedResources<PgSoftDTOResource<DTO_TYPE>> toDTOs(@NotNull @Size(min = 1) Page<DBO_TYPE> dbos, @NotNull Function<Pageable, Link> selfLink) {
        final List<PgSoftDTOResource<DTO_TYPE>> collect = dbos.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new NotFoundException();
        }
        return new PgSoftDTOPagedResources<>(collect, dbos, selfLink);
    }

    public void extendForUpdate(@NotNull Collection<PgSoftMapperPair<DBO_TYPE, DTO_TYPE>> tacMappedPairs) {
        tacMappedPairs.forEach(tacMappedPair -> this.populateDBO(tacMappedPair.dbo, tacMappedPair.dto));
    }
}
