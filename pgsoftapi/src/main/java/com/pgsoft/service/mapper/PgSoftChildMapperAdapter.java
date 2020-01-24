package com.pgsoft.service.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.IParentInjectableDBO;
import com.pgsoft.persistence.impl.jpa.dbo.IPgSoftDBO;
import com.pgsoft.persistence.impl.jpa.repository.IPgSoftJpaRepository;
import com.pgsoft.service.dto.IParentInjectableDTO;
import com.pgsoft.service.exception.NotFoundException;
import lombok.Getter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public abstract class PgSoftChildMapperAdapter<DBO_TYPE extends IParentInjectableDBO
        , DTO_TYPE extends IParentInjectableDTO
        , PARENT_DBO_TYPE extends IPgSoftDBO> extends PgSoftMapper<DBO_TYPE, DTO_TYPE> {
    @Getter
    private final IPgSoftJpaRepository<PARENT_DBO_TYPE> parentRepository;

    protected PgSoftChildMapperAdapter(IPgSoftJpaRepository<PARENT_DBO_TYPE> parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    protected void populateDBO(@NotNull DBO_TYPE dbo, @NotNull DTO_TYPE dto) {
        if (dto.getParentId() != null) {
            this.findParent(dto.getParentId()).ifPresent(dbo::injectParent);
        }

        if (dbo.extractParent() == null) {
            throw new NotFoundException(("Parent not found when extracting"));
        }
        this.populateChildDBO(dbo, dto);
    }

    public abstract void populateChildDBO(@NotNull DBO_TYPE dbo, @NotNull DTO_TYPE dto);

    @NotNull
    public DBO_TYPE toNewDBO(@NotNull PARENT_DBO_TYPE parent, @NotNull DTO_TYPE dto) {
        final DBO_TYPE newDBOInstance = this.getNewDBOInstance();
        newDBOInstance.injectParent(parent);
        this.populateDBO(newDBOInstance, dto);
        return newDBOInstance;
    }

    protected Optional<PARENT_DBO_TYPE> findParent(@Nullable Long parentId) {
        if (parentId == null) {
            return Optional.empty();
        }
        return parentRepository.findById(parentId);
    }
}
