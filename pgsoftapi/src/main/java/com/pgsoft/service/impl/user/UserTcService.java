package com.pgsoft.service.impl.user;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.dbo.UserTc;
import com.pgsoft.persistence.impl.jpa.querydsl.UserTcQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.UserTcRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.user.dto.UserTcDTO;
import com.pgsoft.service.impl.user.mapper.UserTcMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UserTcService extends PgSoftChildServiceAdapter<UserTc, UserTcDTO, User> {
    public UserTcService(UserTcMapper mapper
            , UserTcRepository repository
            , UserTcQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<UserTcDTO>> create(@Nullable Long parentId, @NotNull UserTcDTO newObject) {
        return super.create(parentId, newObject);
    }

    @Override
    public Optional<PgSoftDTOResource<UserTcDTO>> read(@Nullable Long parentId, @Nullable Long id) {
        return super.read(parentId, id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<UserTcDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<UserTcDTO>> update(@Nullable Long parentId, @Nullable UserTcDTO updatedObject) {
        return super.update(parentId, parentId, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long parentId) {
        super.delete(parentId, parentId);
    }
}