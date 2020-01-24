package com.pgsoft.service;

import com.pgsoft.persistence.impl.jpa.dbo.IParentInjectableDBO;
import com.pgsoft.persistence.impl.jpa.dbo.IPgSoftDBO;
import com.pgsoft.persistence.impl.jpa.querydsl.IPgSoftQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.IPgSoftChildJpaRepository;
import com.pgsoft.service.dto.ParentInjectableDTO;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.service.mapper.PgSoftMapperPair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class PgSoftChildServiceAdapter<DBO_TYPE extends IPgSoftDBO & IParentInjectableDBO<PARENT_DBO_TYPE>
        , DTO_TYPE extends ParentInjectableDTO
        , PARENT_DBO_TYPE extends IPgSoftDBO> extends PgSoftServiceAdapter<DBO_TYPE, DTO_TYPE> {

    protected PgSoftChildServiceAdapter(PgSoftChildMapperAdapter<DBO_TYPE, DTO_TYPE, PARENT_DBO_TYPE> mapper
            , IPgSoftChildJpaRepository<DBO_TYPE> repository
            , IPgSoftQueryDsl<DBO_TYPE> searchRepository) {
        super(mapper, repository, searchRepository);
    }

    protected Optional<PgSoftDTOResource<DTO_TYPE>> create(@Nullable Long parentId, @Nullable DTO_TYPE newObject) {
        return this.createDbo(parentId, newObject).map(this.getMapper()::toDTO);
    }

    protected Optional<DBO_TYPE> createDbo(@Nullable Long parentId, @Nullable DTO_TYPE newObject) {
        if (newObject == null) {
            return Optional.empty();
        }
        newObject.setParentId(parentId);
        return this.save(this.getMapper().toNewDBO(newObject));
    }

    protected Optional<PgSoftDTOResource<DTO_TYPE>> read(@Nullable Long id) {
        return this.findById(id).map(this.getMapper()::toDTO);
    }

    protected Optional<PgSoftDTOResource<DTO_TYPE>> read(@Nullable Long parentId, @Nullable Long id) {
        return this.findByParentIdAndId(parentId, id).map(this.getMapper()::toDTO);
    }

    protected Optional<PgSoftDTOPagedResources<PgSoftDTOResource<DTO_TYPE>>> readAll(@Nullable Long parentId, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return this.findByParentId(parentId, pageable).map(dbos -> this.getMapper().toDTOs(dbos, selfLink));
    }

    protected Optional<PgSoftDTOResource<DTO_TYPE>> update(@Nullable Long parentId, @Nullable Long id, @Nullable DTO_TYPE updatedObject) {
        if (updatedObject == null) {
            return Optional.empty();
        }
        return this.findByParentIdAndId(parentId, id).map(dbo -> new PgSoftMapperPair<>(dbo, updatedObject)).flatMap(this::updatePgSoftMapperPair).map(this.getMapper()::toDTO);
    }

    protected Optional<PgSoftDTOResource<DTO_TYPE>> update(@Nullable Long id, @Nullable DTO_TYPE updatedObject) {
        if (updatedObject == null) {
            return Optional.empty();
        }
        return this.findById(id).map(dbo -> new PgSoftMapperPair<>(dbo, updatedObject)).flatMap(this::updatePgSoftMapperPair).map(this.getMapper()::toDTO);
    }

    protected Optional<PgSoftDTOResource<DTO_TYPE>> upsert(@Nullable Supplier<Optional<PARENT_DBO_TYPE>> findParentDboSupplier
            , @Nullable DTO_TYPE dto
            , @Nullable Supplier<Optional<DBO_TYPE>> findDboSupplier) {
        if (findParentDboSupplier == null) {
            return Optional.empty();
        }
        return super.upsert(dto, findDboSupplier, () -> findParentDboSupplier.get().flatMap(parentDbo -> this.create(parentDbo.getId(), dto)));
    }

    protected Optional<PgSoftDTOResource<DTO_TYPE>> upsert(@Nullable Long parentId, @Nullable DTO_TYPE dto, @Nullable Supplier<Optional<DBO_TYPE>> findDboSupplier) {
        return super.upsert(dto, findDboSupplier, () -> this.create(parentId, dto));
    }

    protected void delete(@Nullable Long parentId, @Nullable Long id) {
        this.findByParentIdAndId(parentId, id).ifPresent(this::delete);
    }

    protected void delete(@Nullable Long id) {
        this.findById(id).ifPresent(this::delete);
    }

    private Optional<Page<DBO_TYPE>> findByParentId(@Nullable Long parentId, @Nullable Pageable pageable) {
        if (parentId == null) {
            return Optional.empty();
        }
        return this.makeOptional(((IPgSoftChildJpaRepository<DBO_TYPE>) repository).extractByParentId(parentId, pageable));
    }

    protected Optional<DBO_TYPE> findByParentIdAndId(@Nullable Long parentId, @Nullable Long id) {
        return ((IPgSoftChildJpaRepository<DBO_TYPE>) repository).extractByParentIdAndId(parentId, id);
    }
}
