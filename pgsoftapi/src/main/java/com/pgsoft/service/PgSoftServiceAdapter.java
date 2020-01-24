package com.pgsoft.service;

import com.pgsoft.persistence.impl.jpa.dbo.IPgSoftDBO;
import com.pgsoft.persistence.impl.jpa.querydsl.IPgSoftQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.IPgSoftJpaRepository;
import com.pgsoft.service.dto.IPgSoftDTO;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.mapper.PgSoftMapper;
import com.pgsoft.service.mapper.PgSoftMapperPair;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public abstract class PgSoftServiceAdapter<
        DBO_TYPE extends IPgSoftDBO,
        DTO_TYPE extends IPgSoftDTO> extends PgSoftRepoService<DBO_TYPE> {
    protected final PgSoftMapper<DBO_TYPE, DTO_TYPE> mapper;

    protected PgSoftServiceAdapter(PgSoftMapper<DBO_TYPE, DTO_TYPE> pgSoftMapper
            , IPgSoftJpaRepository<DBO_TYPE> repository
            , IPgSoftQueryDsl<DBO_TYPE> searchRepository) {
        super(repository, searchRepository);
        this.mapper = pgSoftMapper;
    }

    protected Optional<List<DBO_TYPE>> updatePgSoftMapperPair(@Nullable Collection<PgSoftMapperPair<DBO_TYPE, DTO_TYPE>> updates) {
        if (updates == null || updates.isEmpty()) {
            return Optional.empty();
        }
        this.getMapper().extendForUpdate(updates);
        return this.save(updates.stream().map(update -> update.dbo).collect(Collectors.toList()));
    }

    protected Optional<DBO_TYPE> updatePgSoftMapperPair(@Nullable PgSoftMapperPair<DBO_TYPE, DTO_TYPE> update) {
        if (update == null) {
            return Optional.empty();
        }
        return this.updatePgSoftMapperPair(Collections.singletonList(update)).map(Collection::stream).flatMap(Stream::findFirst);
    }

    protected Optional<PgSoftDTOPagedResources<PgSoftDTOResource<DTO_TYPE>>> readAll(@Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return this.readAll(null, pageable, selfLink);
    }

    protected Optional<PgSoftDTOPagedResources<PgSoftDTOResource<DTO_TYPE>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        if (null == pageable) {
            return Optional.empty();
        }
        final Page<DBO_TYPE> page;
        if (predicate == null) {
            page = this.getSearchRepository().findAll(pageable);
        } else {
            page = this.getSearchRepository().findAll(predicate, pageable);
        }
        return this.makeOptional(page).map(dbos -> this.getMapper().toDTOs(dbos, selfLink));
    }

    protected Optional<PgSoftDTOResource<DTO_TYPE>> update(@Nullable DBO_TYPE dboToUpdate, @Nullable DTO_TYPE updatedObject) {
        if (dboToUpdate == null || updatedObject == null) {
            return Optional.empty();
        }
        this.getMapper().extendForUpdate(Collections.singletonList(new PgSoftMapperPair<>(dboToUpdate, updatedObject)));
        return this.save(dboToUpdate).map(this.getMapper()::toDTO);
    }

    protected Optional<PgSoftDTOResource<DTO_TYPE>> upsert(
            @Nullable DTO_TYPE dto,
            @Nullable Supplier<Optional<DBO_TYPE>> findDboSupplier,
            @Nullable Supplier<Optional<PgSoftDTOResource<DTO_TYPE>>> createDtoSupplier) {
        if (dto == null || findDboSupplier == null || createDtoSupplier == null) {
            return Optional.empty();
        }
        final Optional<DBO_TYPE> optionalDbo = findDboSupplier.get();
        if (optionalDbo.isPresent()) {
            return this.update(optionalDbo.get(), dto);
        } else {
            return createDtoSupplier.get();
        }
    }

    protected Optional<PgSoftDTOResource<DTO_TYPE>> cread(@Nullable Supplier<Optional<DBO_TYPE>> findDboSupplier
            , @Nullable Supplier<Optional<PgSoftDTOResource<DTO_TYPE>>> createDTOSupplier) {
        if (findDboSupplier == null || createDTOSupplier == null) {
            return Optional.empty();
        }
        final Optional<DBO_TYPE> optionalDbo = findDboSupplier.get();
        if (optionalDbo.isPresent()) {
            return optionalDbo.map(this.getMapper()::toDTO);
        } else {
            return createDTOSupplier.get();
        }
    }

    public PgSoftMapper<DBO_TYPE, DTO_TYPE> getMapper() {
        return this.mapper;
    }
}
