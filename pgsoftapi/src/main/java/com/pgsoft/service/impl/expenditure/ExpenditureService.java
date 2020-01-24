package com.pgsoft.service.impl.expenditure;

import com.pgsoft.persistence.impl.jpa.dbo.Expenditure;
import com.pgsoft.persistence.impl.jpa.querydsl.ExpenditureQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.ExpenditureRepository;
import com.pgsoft.service.PgSoftParentServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.expenditure.dto.ExpenditureDTO;
import com.pgsoft.service.impl.expenditure.mapper.ExpenditureMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ExpenditureService extends PgSoftParentServiceAdapter<Expenditure, ExpenditureDTO> {
    protected ExpenditureService(ExpenditureMapper mapper
            , ExpenditureRepository repository
            , ExpenditureQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<ExpenditureDTO>> create(@NotNull ExpenditureDTO newObject) {
        return super.create(newObject);
    }

    @Override
    public Optional<PgSoftDTOResource<ExpenditureDTO>> readById(@Nullable Long id) {
        return super.readById(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<ExpenditureDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<ExpenditureDTO>> update(@Nullable Long id, @Nullable ExpenditureDTO updatedObject) {
        return super.update(id, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long id) {
        if (id != null) {
            super.deleteById(id);
        }
    }
}