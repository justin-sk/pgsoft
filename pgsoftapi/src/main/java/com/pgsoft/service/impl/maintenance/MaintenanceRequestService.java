package com.pgsoft.service.impl.maintenance;

import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequest;
import com.pgsoft.persistence.impl.jpa.querydsl.MaintenanceRequestQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.MaintenanceRequestRepository;
import com.pgsoft.service.PgSoftParentServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.SummaryDTO;
import com.pgsoft.service.impl.maintenance.dto.MaintenanceRequestDTO;
import com.pgsoft.service.impl.maintenance.mapper.MaintenanceRequestMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MaintenanceRequestService extends PgSoftParentServiceAdapter<MaintenanceRequest, MaintenanceRequestDTO> {
    protected MaintenanceRequestService(MaintenanceRequestMapper mapper
            , MaintenanceRequestRepository repository
            , MaintenanceRequestQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<MaintenanceRequestDTO>> create(@NotNull MaintenanceRequestDTO newObject) {
        return super.create(newObject);
    }

    @Override
    public Optional<PgSoftDTOResource<MaintenanceRequestDTO>> readById(@Nullable Long id) {
        return super.readById(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<MaintenanceRequestDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<MaintenanceRequestDTO>> update(@Nullable Long id, @Nullable MaintenanceRequestDTO updatedObject) {
        return super.update(id, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long id) {
        if (id != null) {
            super.deleteById(id);
        }
    }

    public Optional<Collection<SummaryDTO>> readSummary(@Nullable Predicate predicate) {
        final Iterable<MaintenanceRequest> maintenanceRequest;
        if (predicate == null) {
            maintenanceRequest = this.getSearchRepository().findAll();
        } else {
            maintenanceRequest = this.getSearchRepository().findAll(predicate);
        }
        return Optional.of(
                StreamSupport.stream(maintenanceRequest.spliterator(), false)
                        .collect(Collectors.groupingBy(req -> req.getStatus().getCode()))
                        .entrySet().stream()
                        .map(item -> new SummaryDTO(item.getKey(), BigInteger.valueOf(item.getValue().size())))
                        .collect(Collectors.toList()));

    }

    public Optional<Collection<SummaryDTO>> monthlySummary(@Nullable Predicate predicate) {
        final Iterable<MaintenanceRequest> maintenanceRequest;
        if (predicate == null) {
            maintenanceRequest = this.getSearchRepository().findAll();
        } else {
            maintenanceRequest = this.getSearchRepository().findAll(predicate);
        }
        return Optional.of(
                StreamSupport.stream(maintenanceRequest.spliterator(), false)
                        .collect(Collectors.groupingBy(req -> new SimpleDateFormat("yyyy-MMMM").format(req.getRequestDate())))
                        .entrySet().stream()
                        .map(item -> new SummaryDTO(item.getKey(), BigInteger.valueOf(item.getValue().size())))
                        .collect(Collectors.toList()));

    }
}