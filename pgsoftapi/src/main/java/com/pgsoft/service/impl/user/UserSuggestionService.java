package com.pgsoft.service.impl.user;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import com.pgsoft.persistence.impl.jpa.dbo.UserSuggestion;
import com.pgsoft.persistence.impl.jpa.querydsl.UserSuggestionQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.UserSuggestionRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.user.dto.UserSuggestionDTO;
import com.pgsoft.service.impl.user.mapper.UserSuggestionMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UserSuggestionService extends PgSoftChildServiceAdapter<UserSuggestion, UserSuggestionDTO, User> {

    protected UserSuggestionService(UserSuggestionMapper mapper
            , UserSuggestionRepository repository
            , UserSuggestionQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<UserSuggestionDTO>> create(@Nullable Long parentId, @NotNull UserSuggestionDTO newObject) {
        return super.create(parentId, newObject);
    }

    @Override
    public Optional<PgSoftDTOResource<UserSuggestionDTO>> read(@Nullable Long parentId, @Nullable Long id) {
        return super.read(parentId, id);
    }

    @Override
    public Optional<PgSoftDTOResource<UserSuggestionDTO>> read(@Nullable Long id) {
        return super.read(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<UserSuggestionDTO>>> readAll(@Nullable Predicate predicate, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<UserSuggestionDTO>> update(@Nullable Long parentId, @Nullable UserSuggestionDTO updatedObject) {
        return super.update(parentId, parentId, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long parentId) {
        super.delete(parentId, parentId);
    }
}
