package com.pgsoft.service.impl.notification;

import com.pgsoft.persistence.impl.jpa.dbo.Notification;
import com.pgsoft.persistence.impl.jpa.querydsl.NotificationQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.NotificationRepository;
import com.pgsoft.service.PgSoftParentServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.notification.dto.NotificationDTO;
import com.pgsoft.service.impl.notification.mapper.NotificationMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class NotificationService extends PgSoftParentServiceAdapter<Notification, NotificationDTO> {
    protected NotificationService(NotificationMapper mapper
            , NotificationRepository repository
            , NotificationQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<NotificationDTO>> create(@NotNull NotificationDTO newObject) {
        return super.create(newObject);
    }

    @Override
    public Optional<PgSoftDTOResource<NotificationDTO>> readById(@Nullable Long id) {
        return super.readById(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<NotificationDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<NotificationDTO>> update(@Nullable Long id, @Nullable NotificationDTO updatedObject) {
        return super.update(id, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long id) {
        if (id != null) {
            super.deleteById(id);
        }
    }
}
