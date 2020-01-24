package com.pgsoft.persistence.impl.jpa.querydsl;

import com.pgsoft.persistence.impl.jpa.dbo.IPgSoftDBO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface IPgSoftQueryDsl<DBO_TYPE extends IPgSoftDBO> extends JpaRepository<DBO_TYPE, Long>, QuerydslPredicateExecutor<DBO_TYPE> {
    @NotNull
    @Override
    List<DBO_TYPE> findAll();

    @NotNull
    @Override
    List<DBO_TYPE> findAll(@NotNull Sort sort);

    @NotNull
    @Override
    List<DBO_TYPE> findAllById(@NotNull Iterable<Long> longs);

    @NotNull
    @Override
    Optional<DBO_TYPE> findById(@NotNull Long id);
}
