package com.pgsoft.service.impl.notification;

import com.pgsoft.persistence.impl.jpa.dbo.Notification;
import com.pgsoft.persistence.impl.jpa.dbo.NotificationDtls;
import com.pgsoft.persistence.impl.jpa.querydsl.NotificationDtlsQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.NotificationDtlsRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.notification.dto.NotificationDtlsDTO;
import com.pgsoft.service.impl.notification.mapper.NotificationDtlsMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class NotificationDtlsService extends PgSoftChildServiceAdapter<NotificationDtls, NotificationDtlsDTO, Notification> {
    protected NotificationDtlsService(NotificationDtlsMapper mapper
            , NotificationDtlsRepository repository
            , NotificationDtlsQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<NotificationDtlsDTO>> create(@Nullable Long parentId, @NotNull NotificationDtlsDTO newObject) {
        return super.create(parentId, newObject);
    }

    @Override
    public Optional<PgSoftDTOResource<NotificationDtlsDTO>> read(@Nullable Long id) {
        return super.read(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<NotificationDtlsDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<NotificationDtlsDTO>> update(@Nullable Long id, @Nullable NotificationDtlsDTO updatedObject) {
        return super.update(id, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long id) {
        if (id != null) {
            super.deleteById(id);
        }
    }
}