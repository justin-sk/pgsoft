package com.pgsoft.service.impl.type;

import com.pgsoft.persistence.impl.jpa.dbo.TypeDBO;
import com.pgsoft.persistence.impl.jpa.querydsl.IPgSoftQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.IPgSoftJpaRepository;
import com.pgsoft.persistence.impl.jpa.repository.IPgSoftTypeJpaRepository;
import com.pgsoft.service.PgSoftParentServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.type.dto.PgSoftTypeDTO;
import com.pgsoft.service.impl.type.mapper.TypeMapperAdapter;

import javax.annotation.Nullable;
import java.util.Optional;

public class TypeServiceAdapter<DBO_TYPE extends TypeDBO> extends PgSoftParentServiceAdapter<DBO_TYPE, PgSoftTypeDTO> {
    protected TypeServiceAdapter(TypeMapperAdapter<DBO_TYPE> mapper,
                                 IPgSoftJpaRepository<DBO_TYPE> repository,
                                 IPgSoftQueryDsl<DBO_TYPE> searchRepository) {
        super(mapper, repository, searchRepository);
    }

    protected Optional<PgSoftDTOResource<PgSoftTypeDTO>> createByCode(@Nullable String code) {
        if (code == null || this.readByCode(code).isPresent()) {
            return Optional.empty();
        }
        return this.create(new PgSoftTypeDTO(code));
    }

    public Optional<PgSoftDTOResource<PgSoftTypeDTO>> creadByCode(@Nullable String code) {
        return this.creadDBO(code).map(this.getMapper()::toDTO);
    }

    public Optional<DBO_TYPE> creadDBO(@Nullable String code) {
        if (code == null || code.isEmpty()) {
            return Optional.empty();
        }
        final Optional<DBO_TYPE> optionalDBO = this.findByCode(code);
        if (optionalDBO.isPresent()) {
            return optionalDBO;
        } else {
            return createDBO(new PgSoftTypeDTO(code));
        }
    }

    public Optional<DBO_TYPE> findByCode(@Nullable String code) {
        if (code == null || code.isEmpty()) {
            return Optional.empty();
        }
        return ((IPgSoftTypeJpaRepository<DBO_TYPE>) this.getRepository()).findOneByCode(code);
    }

    protected Optional<PgSoftDTOResource<PgSoftTypeDTO>> readByCode(@Nullable String code) {
        if (code == null || code.isEmpty()) {
            return Optional.empty();
        }
        return findByCode(code).map(this.getMapper()::toDTO);
    }

    protected void deleteByCode(@Nullable String code) {
        this.findByCode(code).ifPresent(this::delete);
    }
}

