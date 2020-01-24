package com.pgsoft.service.impl.user;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.dbo.UserRole;
import com.pgsoft.persistence.impl.jpa.querydsl.UserRoleQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.UserRoleRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.impl.user.mapper.UserRoleMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UserRoleService extends PgSoftChildServiceAdapter<UserRole, XRefDTO, User> {
    public UserRoleService(UserRoleMapper mapper
            , UserRoleRepository repository
            , UserRoleQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<XRefDTO>> create(@Nullable Long parentId, @NotNull XRefDTO newObject) {
        return super.create(parentId, newObject);
    }

    @Override
    public Optional<PgSoftDTOResource<XRefDTO>> read(@Nullable Long parentId, @Nullable Long id) {
        return super.read(parentId, id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<XRefDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public void delete(@Nullable Long parentId, @Nullable Long id) {
        super.delete(parentId, id);
    }
}
