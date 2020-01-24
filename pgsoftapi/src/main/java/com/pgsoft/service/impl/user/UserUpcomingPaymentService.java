package com.pgsoft.service.impl.user;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.dbo.UserUpcomingPayment;
import com.pgsoft.persistence.impl.jpa.querydsl.UserUpcomingPaymentQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.UserUpcomingPaymentRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.user.dto.UserUpcomingPaymentDTO;
import com.pgsoft.service.impl.user.mapper.UserUpcomingPaymentMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UserUpcomingPaymentService extends PgSoftChildServiceAdapter<UserUpcomingPayment, UserUpcomingPaymentDTO, User> {
    protected UserUpcomingPaymentService(UserUpcomingPaymentMapper mapper
            , UserUpcomingPaymentRepository repository
            , UserUpcomingPaymentQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<UserUpcomingPaymentDTO>> create(@Nullable Long parentId, @NotNull UserUpcomingPaymentDTO newObject) {
        return super.create(parentId, newObject);
    }

    @Override
    public Optional<PgSoftDTOResource<UserUpcomingPaymentDTO>> read(@Nullable Long id) {
        return super.read(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<UserUpcomingPaymentDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<UserUpcomingPaymentDTO>> update(@Nullable Long parentId, @Nullable UserUpcomingPaymentDTO updatedObject) {
        return super.update(parentId, parentId, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long parentId) {
        super.delete(parentId, parentId);
    }
}