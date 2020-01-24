package com.pgsoft.service.impl.type;

import com.pgsoft.persistence.impl.jpa.dbo.PaymentType;
import com.pgsoft.persistence.impl.jpa.querydsl.PaymentTypeQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.PaymentTypeRepository;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.type.dto.PgSoftTypeDTO;
import com.pgsoft.service.impl.type.mapper.PaymentTypeMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class PaymentTypeService  extends TypeServiceAdapter<PaymentType>  {
    protected PaymentTypeService(PaymentTypeMapper mapper
            , PaymentTypeRepository repository
            , PaymentTypeQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<PgSoftTypeDTO>> readByCode(@Nullable String code) {
        return super.readByCode(code);
    }

    @Override
    public Optional<PgSoftDTOResource<PgSoftTypeDTO>> readById(@Nullable Long id) {
        return super.readById(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<PgSoftTypeDTO>>> readAll(@Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<PgSoftTypeDTO>> update(@Nullable Long id,@Nullable PgSoftTypeDTO updatedObject) {
        return super.update(id,updatedObject);
    }

    @Override
    public void deleteByCode(@Nullable String code) {
        super.deleteByCode(code);
    }

    @Override
    public void deleteById(@Nullable Long aLong) {
        super.deleteById(aLong);
    }
}
