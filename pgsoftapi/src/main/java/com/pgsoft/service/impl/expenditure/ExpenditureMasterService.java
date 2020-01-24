package com.pgsoft.service.impl.expenditure;

import com.pgsoft.persistence.impl.jpa.dbo.ExpenditureMaster;
import com.pgsoft.persistence.impl.jpa.querydsl.ExpenditureMasterQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.ExpenditureMasterRepository;
import com.pgsoft.service.PgSoftParentServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.expenditure.dto.ExpenditureMasterDTO;
import com.pgsoft.service.impl.expenditure.mapper.ExpenditureMasterMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ExpenditureMasterService extends PgSoftParentServiceAdapter<ExpenditureMaster, ExpenditureMasterDTO> {
    protected ExpenditureMasterService(ExpenditureMasterMapper mapper
            , ExpenditureMasterRepository repository
            , ExpenditureMasterQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<ExpenditureMasterDTO>> create(@NotNull ExpenditureMasterDTO newObject) {
        return super.create(newObject);
    }

    @Override
    public Optional<PgSoftDTOResource<ExpenditureMasterDTO>> readById(@Nullable Long id) {
        return super.readById(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<ExpenditureMasterDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<ExpenditureMasterDTO>> update(@Nullable Long id, @Nullable ExpenditureMasterDTO updatedObject) {
        return super.update(id, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long id) {
        if (id != null) {
            super.deleteById(id);
        }
    }
}