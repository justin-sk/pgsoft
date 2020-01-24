package com.pgsoft.service.impl.maintenance;

import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequest;
import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequestAssignTo;
import com.pgsoft.persistence.impl.jpa.querydsl.MaintenanceRequestAssignToQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.MaintenanceRequestAssignToRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.impl.maintenance.mapper.MaintenanceRequestAssignToMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class MaintenanceRequestAssignToService extends PgSoftChildServiceAdapter<MaintenanceRequestAssignTo, XRefDTO, MaintenanceRequest> {
    protected MaintenanceRequestAssignToService(MaintenanceRequestAssignToMapper mapper
            , MaintenanceRequestAssignToRepository repository
            , MaintenanceRequestAssignToQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    public Optional<PgSoftDTOResource<XRefDTO>> read(@Nullable Long parentId, @Nullable Long id) {
        return super.read(parentId, id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<XRefDTO>>> readAll(@Nullable Long parentId, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(parentId, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<XRefDTO>>> readAll(Predicate predicate, Pageable pageable, Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    public Optional<PgSoftDTOResource<XRefDTO>> create(@Nullable Long parentId, @Nullable XRefDTO dto) {
        dto.setParentId(parentId);
        return super.save(this.getMapper().toNewDBO(dto)).map(this.getMapper()::toDTO);
    }

    public void deleteById(@Nullable Long parentId, @Nullable Long id) {
        super.delete(parentId, id);
    }
}
