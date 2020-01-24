package com.pgsoft.service;

import com.pgsoft.persistence.impl.jpa.dbo.IPgSoftDBO;
import com.pgsoft.persistence.impl.jpa.querydsl.IPgSoftQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.IPgSoftJpaRepository;
import com.pgsoft.service.dto.IPgSoftDTO;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.PgSoftDTOResources;
import com.pgsoft.service.mapper.PgSoftMapperPair;
import com.pgsoft.service.mapper.PgSoftParentMapperAdapter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
public class PgSoftParentServiceAdapter<DBO_TYPE extends IPgSoftDBO, DTO_TYPE extends IPgSoftDTO> extends PgSoftServiceAdapter<DBO_TYPE, DTO_TYPE> {
    protected PgSoftParentServiceAdapter(PgSoftParentMapperAdapter<DBO_TYPE, DTO_TYPE> mapper
            , IPgSoftJpaRepository<DBO_TYPE> repository
            , IPgSoftQueryDsl<DBO_TYPE> searchRepository) {
        super(mapper, repository, searchRepository);
    }

    protected Optional<PgSoftDTOResource<DTO_TYPE>> create(@Nullable DTO_TYPE newObject) {
        return this.createDBO(newObject).map(this.getMapper()::toDTO);
    }

    protected Optional<DBO_TYPE> createDBO(DTO_TYPE dto) {
        if (dto == null) {
            return Optional.empty();
        }
        return this.save(this.getMapper().toNewDBO(dto));
    }

    private Supplier<Optional<List<DBO_TYPE>>> getCreateDBOSupplier(@Nullable List<DTO_TYPE> dtos) {
        if (dtos == null) {
            return Optional::empty;
        }
        return () -> this.save(((PgSoftParentMapperAdapter) this.getMapper()).toNewDBOs(dtos));
    }

    protected Optional<PgSoftDTOResources<PgSoftDTOResource<DTO_TYPE>>> create(@Nullable List<DTO_TYPE> newObjects) {
        if (newObjects == null || newObjects.isEmpty()) {
            return Optional.empty();
        }
        return this.getCreateDBOSupplier(newObjects).get().map(this.getMapper()::toDTOs);
    }

    protected Optional<PgSoftDTOResource<DTO_TYPE>> readById(@Nullable Long id) {
        return super.findById(id).map(this.getMapper()::toDTO);
    }

    protected Optional<PgSoftDTOResource<DTO_TYPE>> update(@Nullable Long id, @Nullable DTO_TYPE updatedObject) {
        if (updatedObject == null) {
            return Optional.empty();
        }
        return super.findById(id).map(dbo -> new PgSoftMapperPair<>(dbo, updatedObject)).flatMap(this::updatePgSoftMapperPair).map(this.getMapper()::toDTO);
    }

    protected Optional<PgSoftDTOResource<DTO_TYPE>> upsert(@Nullable DTO_TYPE dto, @Nullable Supplier<Optional<DBO_TYPE>> findDboSupplier) {
        return this.upsert(dto, findDboSupplier, () -> this.create(dto));
    }

    protected void delete(Supplier<Optional<DBO_TYPE>> findDboSupplier) {
        findDboSupplier.get().ifPresent(this::delete);
    }

    protected void delete(@Nullable Iterable<? extends DBO_TYPE> iterable) {
        if (iterable != null && iterable.iterator().hasNext()) {
            this.getRepository().deleteAll(iterable);
        }
    }
}
