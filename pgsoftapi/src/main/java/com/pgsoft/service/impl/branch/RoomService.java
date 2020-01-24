package com.pgsoft.service.impl.branch;

import com.pgsoft.persistence.impl.jpa.dbo.Branch;
import com.pgsoft.persistence.impl.jpa.dbo.Room;
import com.pgsoft.persistence.impl.jpa.querydsl.RoomQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.RoomRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.branch.dto.RoomDTO;
import com.pgsoft.service.impl.branch.mapper.RoomMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class RoomService extends PgSoftChildServiceAdapter<Room, RoomDTO, Branch> {
    public RoomService(RoomMapper mapper
            , RoomRepository repository
            , RoomQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    public Optional<PgSoftDTOResource<RoomDTO>> create(@NotNull Long branchId, @NotNull RoomDTO newObject) {
        return super.create(branchId, newObject);
    }

    public Optional<PgSoftDTOResource<RoomDTO>> readById(@Nullable Long id) {
        return super.read(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<RoomDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    public Optional<PgSoftDTOResource<RoomDTO>> update(@Nullable Long id, @Nullable RoomDTO updatedObject) {
        return super.update(id, id, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long id) {
        if (id != null) {
            super.deleteById(id);
        }
    }
}