package com.pgsoft.service.impl.menu;

import com.pgsoft.persistence.impl.jpa.dbo.Menu;
import com.pgsoft.persistence.impl.jpa.querydsl.MenuQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.MenuRepository;
import com.pgsoft.service.PgSoftParentServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.impl.menu.dto.MenuDTO;
import com.pgsoft.service.impl.menu.mapper.MenuMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class MenuService extends PgSoftParentServiceAdapter<Menu, MenuDTO> {
    protected MenuService(MenuMapper mapper
            , MenuRepository repository
            , MenuQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    @Override
    public Optional<PgSoftDTOResource<MenuDTO>> readById(@Nullable Long id) {
        return super.readById(id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<MenuDTO>>> readAll(@Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOResource<MenuDTO>> create(@Nullable MenuDTO newObject) {
        return super.create(newObject);
    }

    @Override
    public Optional<PgSoftDTOResource<MenuDTO>> update(@Nullable Long id, @Nullable MenuDTO updatedObject) {
        return super.update(id, updatedObject);
    }

    @Override
    public void deleteById(@Nullable Long aLong) {
        super.deleteById(aLong);
    }
}
