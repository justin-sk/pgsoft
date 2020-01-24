package com.pgsoft.service.impl.maintenance;

import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequest;
import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequestFee;
import com.pgsoft.persistence.impl.jpa.querydsl.MaintenanceRequestFeeQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.MaintenanceRequestFeeRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.maintenance.dto.MaintenanceRequestFeeDTO;
import com.pgsoft.service.impl.maintenance.mapper.MaintenanceRequestFeeMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class MaintenanceRequestFeeService extends PgSoftChildServiceAdapter<MaintenanceRequestFee, MaintenanceRequestFeeDTO, MaintenanceRequest> {
    protected MaintenanceRequestFeeService(MaintenanceRequestFeeMapper mapper
            , MaintenanceRequestFeeRepository repository
            , MaintenanceRequestFeeQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    public Optional<PgSoftDTOResource<MaintenanceRequestFeeDTO>> create(@Nullable Long parentId, @NotNull MaintenanceRequestFeeDTO newObject) {
        return super.create(parentId, newObject);
    }

    public Optional<PgSoftDTOResource<MaintenanceRequestFeeDTO>> readById(@Nullable Long parentId) {
        return super.read(parentId);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<MaintenanceRequestFeeDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<MaintenanceRequestFeeDTO>> update(@Nullable Long parentId, @Nullable MaintenanceRequestFeeDTO updatedObject) {
        return super.update(parentId, parentId, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long parentId) {
        super.delete(parentId, parentId);
    }
}
