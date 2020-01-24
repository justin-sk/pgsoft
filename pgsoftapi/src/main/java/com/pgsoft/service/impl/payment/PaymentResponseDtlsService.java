package com.pgsoft.service.impl.payment;

import com.pgsoft.persistence.impl.jpa.dbo.PaymentDtls;
import com.pgsoft.persistence.impl.jpa.dbo.PaymentResponseDtls;
import com.pgsoft.persistence.impl.jpa.querydsl.PaymentResponseDtlsQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.PaymentResponseDtlsRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.payment.dto.PaymentResponseDtlsDTO;
import com.pgsoft.service.impl.payment.mapper.PaymentResponseDtlsMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class PaymentResponseDtlsService extends PgSoftChildServiceAdapter<PaymentResponseDtls, PaymentResponseDtlsDTO, PaymentDtls> {
    protected PaymentResponseDtlsService(PaymentResponseDtlsMapper mapper
            , PaymentResponseDtlsRepository repository
            , PaymentResponseDtlsQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<PaymentResponseDtlsDTO>> create(@Nullable Long parentId, @NotNull PaymentResponseDtlsDTO newObject) {
        return super.create(parentId, newObject);
    }

    public Optional<PgSoftDTOResource<PaymentResponseDtlsDTO>> read(@Nullable Long parentId) {
        return super.read(parentId, parentId);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<PaymentResponseDtlsDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<PaymentResponseDtlsDTO>> update(@Nullable Long parentId, @Nullable PaymentResponseDtlsDTO updatedObject) {
        return super.update(parentId, parentId, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long parentId) {
        super.delete(parentId, parentId);
    }
}