package com.pgsoft.service.impl.amenity;

import com.pgsoft.persistence.impl.jpa.dbo.Amenity;
import com.pgsoft.persistence.impl.jpa.querydsl.AmenityQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.AmenityRepository;
import com.pgsoft.service.PgSoftServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.amenity.dto.AmenityDTO;
import com.pgsoft.service.impl.amenity.mapper.AmenityMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Function;

@Service
public class AmenityService extends PgSoftServiceAdapter<Amenity, AmenityDTO> {
    protected AmenityService(AmenityMapper mapper, AmenityRepository repository, AmenityQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    public Optional<PgSoftDTOResource<AmenityDTO>> readById(@Nullable Long id) {
        return super.findById(id).map(this.getMapper()::toDTO);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<AmenityDTO>>> readAll(Pageable pageable, Function<Pageable, Link> selfLink) {
        return super.readAll(pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<AmenityDTO>>> readAll(Predicate predicate, Pageable pageable, Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    public Optional<PgSoftDTOResource<AmenityDTO>> create(@Nullable AmenityDTO newObject) {
        return super.save(this.getMapper().toNewDBO(newObject)).map(this.getMapper()::toDTO);
    }

    public Optional<PgSoftDTOResource<AmenityDTO>> update(@Nullable Long id, @Nullable AmenityDTO updatedObject) {
        return super.update(super.findById(id).orElseThrow(NotFoundException::new), updatedObject);
    }

    public void deleteById(@Nullable Long id) {
        super.deleteById(id);
    }
}
