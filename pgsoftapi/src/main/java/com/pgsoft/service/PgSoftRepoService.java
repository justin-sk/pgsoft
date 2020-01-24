package com.pgsoft.service;

import com.pgsoft.persistence.impl.jpa.dbo.IPgSoftDBO;
import com.pgsoft.persistence.impl.jpa.querydsl.IPgSoftQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.IPgSoftJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Nullable;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Transactional
public abstract class PgSoftRepoService<DBO_TYPE extends IPgSoftDBO> {
    protected final IPgSoftJpaRepository<DBO_TYPE> repository;

    protected final IPgSoftQueryDsl<DBO_TYPE> searchRepository;

    @java.beans.ConstructorProperties({"repository", "searchRepository"})
    public PgSoftRepoService(IPgSoftJpaRepository<DBO_TYPE> repository, IPgSoftQueryDsl<DBO_TYPE> searchRepository) {
        this.repository = repository;
        this.searchRepository = searchRepository;
    }

    protected <R, T extends Iterable<R>> Optional<T> makeOptional(@Nullable T iterable) {
        if (iterable != null && iterable.iterator().hasNext()) {
            return Optional.of(iterable);
        } else {
            return Optional.empty();
        }
    }

    protected Optional<Page<DBO_TYPE>> findAll(@Nullable Pageable pageable) {
        if (null == pageable) {
            return Optional.empty();
        }
        return this.makeOptional(this.getSearchRepository().findAll(pageable));
    }

    protected Optional<Page<DBO_TYPE>> findAll(@Nullable Example<DBO_TYPE> searchExample, @Nullable Pageable pageable) {
        if (searchExample == null || pageable == null) {
            return Optional.empty();
        }
        return this.makeOptional(this.getSearchRepository().findAll(searchExample, pageable));
    }

    public Optional<DBO_TYPE> findById(@Nullable Long aLong) {
        if (aLong == null) {
            return Optional.empty();
        }
        return this.getSearchRepository().findById(aLong);
    }

    protected Optional<List<DBO_TYPE>> save(@Nullable Iterable<DBO_TYPE> iterable) {
        if (iterable == null || !iterable.iterator().hasNext()) {
            return Optional.empty();
        }
        return this.makeOptional(this.getRepository().saveAll(iterable));
    }

    protected Optional<DBO_TYPE> save(@Nullable DBO_TYPE dbo_type) {
        if (dbo_type == null) {
            return Optional.empty();
        }
        return Optional.of(this.getRepository().save(dbo_type));
    }

    protected void deleteById(@Nullable Long aLong) {
        if (aLong != null) {
            this.getSearchRepository().findById(aLong).ifPresent(this::delete);
        }
    }

    protected void delete(@Nullable DBO_TYPE dbo_type) {
        if (dbo_type != null) {
            this.getRepository().delete(dbo_type);
        }
    }

    protected void delete(Supplier<Optional<DBO_TYPE>> dboTypeSupplier) {
        dboTypeSupplier.get().ifPresent(this::delete);
    }

    protected void delete(@Nullable Iterable<? extends DBO_TYPE> iterable) {
        if (iterable != null && iterable.iterator().hasNext()) {
            this.getRepository().deleteAll(iterable);
        }
    }

    public IPgSoftJpaRepository<DBO_TYPE> getRepository() {
        return this.repository;
    }

    public IPgSoftQueryDsl<DBO_TYPE> getSearchRepository() {
        return this.searchRepository;
    }
}
