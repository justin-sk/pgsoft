package com.pgsoft.service.impl.expenditure;

import com.pgsoft.persistence.impl.jpa.dbo.Expenditure;
import com.pgsoft.persistence.impl.jpa.dbo.ExpenditureAttachments;
import com.pgsoft.persistence.impl.jpa.querydsl.ExpenditureAttachmentsQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.ExpenditureAttachmentsRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.expenditure.dto.ExpenditureAttachmentsDTO;
import com.pgsoft.service.impl.expenditure.mapper.ExpenditureAttachmentsMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ExpenditureAttachmentsService extends PgSoftChildServiceAdapter<ExpenditureAttachments, ExpenditureAttachmentsDTO, Expenditure> {
    protected ExpenditureAttachmentsService(ExpenditureAttachmentsMapper mapper
            , ExpenditureAttachmentsRepository repository
            , ExpenditureAttachmentsQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    public Optional<PgSoftDTOResource<ExpenditureAttachmentsDTO>> create(@NotNull Long expenditureId, @NotNull ExpenditureAttachmentsDTO newObject) {
        return super.create(expenditureId, newObject);
    }

    public Optional<PgSoftDTOResource<ExpenditureAttachmentsDTO>> readById(@Nullable Long id) {
        return super.read(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<ExpenditureAttachmentsDTO>>> readAll(@Nullable Long parentId, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(parentId, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<ExpenditureAttachmentsDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<ExpenditureAttachmentsDTO>> update(@Nullable Long id, @Nullable ExpenditureAttachmentsDTO updatedObject) {
        return super.update(id, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long id) {
        if (id != null) {
            super.deleteById(id);
        }
    }
}