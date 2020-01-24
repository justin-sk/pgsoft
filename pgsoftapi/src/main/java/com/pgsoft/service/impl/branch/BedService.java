package com.pgsoft.service.impl.branch;

import com.pgsoft.persistence.impl.jpa.dbo.Bed;
import com.pgsoft.persistence.impl.jpa.dbo.Room;
import com.pgsoft.persistence.impl.jpa.querydsl.BedQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.BedRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.SummaryDTO;
import com.pgsoft.service.impl.branch.dto.BedDTO;
import com.pgsoft.service.impl.branch.mapper.BedMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BedService extends PgSoftChildServiceAdapter<Bed, BedDTO, Room> {
    public BedService(BedMapper mapper
            , BedRepository repository
            , BedQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    public Optional<PgSoftDTOResource<BedDTO>> create(@NotNull Long roomId, @NotNull BedDTO newObject) {
        return super.create(roomId, newObject);
    }

    public Optional<PgSoftDTOResource<BedDTO>> readById(@Nullable Long id) {
        return super.read(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<BedDTO>>> readAll(@Nullable Long parentId, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(parentId, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<BedDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    public Optional<PgSoftDTOResource<BedDTO>> update(@Nullable Long id, @Nullable BedDTO updatedObject) {
        return super.update(id, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long id) {
        if (id != null) {
            super.deleteById(id);
        }
    }

    public Optional<Collection<SummaryDTO>> readSummary(@Nullable Predicate predicate) {
        final Iterable<Bed> beds;
        if (predicate == null) {
            beds = this.getSearchRepository().findAll();
        } else {
            beds = this.getSearchRepository().findAll(predicate);
        }
        return Optional.of(StreamSupport.stream(beds.spliterator(), false)
                .collect(Collectors.groupingBy(req -> req.getUserBed() == null ? "Vacant'" : "Occupied"))
                .entrySet().stream()
                .map(item -> new SummaryDTO(item.getKey(), BigInteger.valueOf(item.getValue().size())))
                .collect(Collectors.toList()));
    }
}
