package com.pgsoft.service.impl.user;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.dbo.UserExitDtls;
import com.pgsoft.persistence.impl.jpa.querydsl.UserExitDtlsQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.UserExitDtlsRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.user.dto.UserExitDtlsDTO;
import com.pgsoft.service.impl.user.mapper.UserExitDtlsMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UserExitDtlsService extends PgSoftChildServiceAdapter<UserExitDtls, UserExitDtlsDTO, User> {
    public UserExitDtlsService(UserExitDtlsMapper mapper
            , UserExitDtlsRepository repository
            , UserExitDtlsQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<UserExitDtlsDTO>> create(@Nullable Long parentId, @NotNull UserExitDtlsDTO newObject) {
        return super.create(parentId, newObject);
    }

    @Override
    public Optional<PgSoftDTOResource<UserExitDtlsDTO>> read(@Nullable Long parentId, @Nullable Long id) {
        return super.read(parentId, id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<UserExitDtlsDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<UserExitDtlsDTO>> update(@Nullable Long parentId, @Nullable UserExitDtlsDTO updatedObject) {
        return super.update(parentId, parentId, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long parentId) {
        super.delete(parentId, parentId);
    }
}
