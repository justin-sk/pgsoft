package com.pgsoft.service.impl.branch;

import com.pgsoft.persistence.impl.jpa.dbo.Branch;
import com.pgsoft.persistence.impl.jpa.dbo.BranchInfo;
import com.pgsoft.persistence.impl.jpa.querydsl.BranchInfoQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.BranchInfoRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.branch.dto.BranchInfoDTO;
import com.pgsoft.service.impl.branch.mapper.BranchInfoMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class BranchInfoService extends PgSoftChildServiceAdapter<BranchInfo, BranchInfoDTO, Branch> {
    public BranchInfoService(BranchInfoMapper mapper
            , BranchInfoRepository repository
            , BranchInfoQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    public Optional<PgSoftDTOResource<BranchInfoDTO>> create(@NotNull BranchInfoDTO newObject) {
        return this.save(this.getMapper().toNewDBO(newObject)).map(this.getMapper()::toDTO);
    }

    public Optional<PgSoftDTOResource<BranchInfoDTO>> readById(@Nullable Long id) {
        return super.read(id, id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<BranchInfoDTO>>> readAll(@Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<BranchInfoDTO>>> readAll(@Nullable Long parentId, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(parentId, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<BranchInfoDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    public Optional<PgSoftDTOResource<BranchInfoDTO>> update(@Nullable Long id, @Nullable BranchInfoDTO updatedObject) {
        return super.update(id, id, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long id) {
        if (id != null) {
            ((BranchInfoMapper) this.getMapper()).getParentRepository().deleteById(id);
        }
    }
}
