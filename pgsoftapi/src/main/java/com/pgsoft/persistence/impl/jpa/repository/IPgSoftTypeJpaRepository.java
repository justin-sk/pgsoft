package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.IPgSoftDBO;
import org.springframework.data.repository.NoRepositoryBean;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@NoRepositoryBean
public interface IPgSoftTypeJpaRepository<DBO_TYPE extends IPgSoftDBO> extends IPgSoftJpaRepository<DBO_TYPE> {
    Optional<DBO_TYPE> findOneByCode(@NotNull String code);
}
