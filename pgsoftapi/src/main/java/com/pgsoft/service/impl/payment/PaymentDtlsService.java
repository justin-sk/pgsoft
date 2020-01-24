package com.pgsoft.service.impl.payment;

import com.pgsoft.persistence.impl.jpa.dbo.PaymentDtls;
import com.pgsoft.persistence.impl.jpa.querydsl.PaymentDtlsQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.PaymentDtlsRepository;
import com.pgsoft.service.PgSoftParentServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.SummaryDTO;
import com.pgsoft.service.impl.payment.dto.PaymentDtlsDTO;
import com.pgsoft.service.impl.payment.mapper.PaymentDtlsMapper;
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
public class PaymentDtlsService extends PgSoftParentServiceAdapter<PaymentDtls, PaymentDtlsDTO> {
    protected PaymentDtlsService(PaymentDtlsMapper mapper
            , PaymentDtlsRepository repository
            , PaymentDtlsQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<PaymentDtlsDTO>> create(@NotNull PaymentDtlsDTO newObject) {
        return super.create(newObject);
    }

    @Override
    public Optional<PgSoftDTOResource<PaymentDtlsDTO>> readById(@Nullable Long id) {
        return super.readById(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<PaymentDtlsDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<PaymentDtlsDTO>> update(@Nullable Long id, @Nullable PaymentDtlsDTO updatedObject) {
        return super.update(id, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long id) {
        if (id != null) {
            super.deleteById(id);
        }
    }

    public Optional<Collection<SummaryDTO>> readSummary(@Nullable Predicate predicate) {
        final Iterable<PaymentDtls> paymentDetails;
        if (predicate == null) {
            paymentDetails = this.getSearchRepository().findAll();
        } else {
            paymentDetails = this.getSearchRepository().findAll(predicate);
        }
        return Optional.of(StreamSupport.stream(paymentDetails.spliterator(), false)
                .collect(Collectors.groupingBy(req -> req.getPaymentType().getCode()))
                .entrySet().stream()
                .map(item -> new SummaryDTO(item.getKey(), BigInteger.valueOf(item.getValue().stream().mapToLong(payment -> payment.getAmount().longValue()).sum())))
                .collect(Collectors.toList()));
    }

    public Optional<Collection<SummaryDTO>> incomeReport(@Nullable Predicate predicate) {
        final Iterable<PaymentDtls> paymentDetails;
        if (predicate == null) {
            paymentDetails = this.getSearchRepository().findAll();
        } else {
            paymentDetails = this.getSearchRepository().findAll(predicate);
        }
        return Optional.of(StreamSupport.stream(paymentDetails.spliterator(), false)
                .collect(Collectors.groupingBy(PaymentDtls::getPaymentDt))
                .entrySet().stream()
                .map(item -> new SummaryDTO(item.getKey().toString(), BigInteger.valueOf(item.getValue().stream().mapToLong(payment -> payment.getAmount().longValue()).sum())))
                .collect(Collectors.toList()));
    }

    public Optional<Collection<SummaryDTO>> revenueReport(@Nullable Predicate predicate) {
        final Iterable<PaymentDtls> paymentDetails;
        if (predicate == null) {
            paymentDetails = this.getSearchRepository().findAll();
        } else {
            paymentDetails = this.getSearchRepository().findAll(predicate);
        }
        return Optional.of(StreamSupport.stream(paymentDetails.spliterator(), false)
                .collect(Collectors.groupingBy(req -> new SimpleDateFormat("yyyy-MMMM").format(req.getPaymentDt())))
                .entrySet().stream()
                .map(item -> new SummaryDTO(item.getKey(), BigInteger.valueOf(item.getValue().stream().mapToLong(payment -> payment.getAmount().longValue()).sum())))
                .collect(Collectors.toList()));
    }
}