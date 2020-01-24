package com.pgsoft.service.impl.branch;

import com.pgsoft.persistence.impl.jpa.dbo.Branch;
import com.pgsoft.persistence.impl.jpa.dbo.BranchContactInfo;
import com.pgsoft.persistence.impl.jpa.querydsl.BranchContactInfoQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.BranchContactInfoRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.branch.dto.BranchContactInfoDTO;
import com.pgsoft.service.impl.branch.mapper.BranchContactInfoMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class BranchContactInfoService extends PgSoftChildServiceAdapter<BranchContactInfo, BranchContactInfoDTO, Branch> {
    public BranchContactInfoService(BranchContactInfoMapper mapper
            , BranchContactInfoRepository repository
            , BranchContactInfoQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    public Optional<PgSoftDTOResource<BranchContactInfoDTO>> create(@NotNull Long branchId, @NotNull BranchContactInfoDTO newObject) {
        return super.create(branchId, newObject);
    }

    public Optional<PgSoftDTOResource<BranchContactInfoDTO>> readById(@Nullable Long id) {
        return super.read(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<BranchContactInfoDTO>>> readAll(@Nullable Long parentId, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(parentId, pageable, selfLink);
    }

    public Optional<PgSoftDTOResource<BranchContactInfoDTO>> update(@Nullable Long id, @Nullable BranchContactInfoDTO updatedObject) {
        return super.update(id, id, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long id) {
        if (id != null) {
            super.deleteById(id);
        }
    }
}
