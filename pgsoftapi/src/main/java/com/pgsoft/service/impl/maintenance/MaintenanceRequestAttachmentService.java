package com.pgsoft.service.impl.maintenance;

import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequest;
import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequestAttachment;
import com.pgsoft.persistence.impl.jpa.querydsl.MaintenanceRequestAttachmentQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.MaintenanceRequestAttachmentRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.maintenance.dto.MaintenanceRequestAttachmentDTO;
import com.pgsoft.service.impl.maintenance.mapper.MaintenanceRequestAttachmentMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class MaintenanceRequestAttachmentService extends PgSoftChildServiceAdapter<MaintenanceRequestAttachment, MaintenanceRequestAttachmentDTO, MaintenanceRequest> {
    protected MaintenanceRequestAttachmentService(MaintenanceRequestAttachmentMapper mapper
            , MaintenanceRequestAttachmentRepository repository
            , MaintenanceRequestAttachmentQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<MaintenanceRequestAttachmentDTO>> create(@Nullable Long parentId, @NotNull MaintenanceRequestAttachmentDTO newObject) {
        return super.create(parentId, newObject);
    }

    @Override
    public Optional<PgSoftDTOResource<MaintenanceRequestAttachmentDTO>> read(@Nullable Long id) {
        return super.read(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<MaintenanceRequestAttachmentDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<MaintenanceRequestAttachmentDTO>> update(@Nullable Long id, @Nullable MaintenanceRequestAttachmentDTO updatedObject) {
        return super.update(id, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long id) {
        if (id != null) {
            super.deleteById(id);
        }
    }
}
