package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.IPgSoftDBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@NoRepositoryBean
public interface IPgSoftChildJpaRepository<DBO_TYPE extends IPgSoftDBO> extends IPgSoftJpaRepository<DBO_TYPE> {
    Page<DBO_TYPE> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    Optional<DBO_TYPE> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
