package com.pgsoft.service.impl.menu;

import com.pgsoft.persistence.impl.jpa.dbo.Menu;
import com.pgsoft.persistence.impl.jpa.dbo.MenuSubMenuReference;
import com.pgsoft.persistence.impl.jpa.querydsl.MenuSubMenuReferenceQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.MenuSubMenuReferenceRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.menu.mapper.MenuSubMenuMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class MenuSubMenuService extends PgSoftChildServiceAdapter<MenuSubMenuReference, XRefDTO, Menu> {
    protected MenuSubMenuService(MenuSubMenuMapper mapper
            , MenuSubMenuReferenceRepository repository
            , MenuSubMenuReferenceQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    public Optional<PgSoftDTOResource<XRefDTO>> read(@Nullable Long parentId, @Nullable Long id) {
        return super.read(parentId, id);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<XRefDTO>>> readAll(@Nullable Long parentId, @Nullable Pageable pageable, @NotNull Function<Pageable, Link> selfLink) {
        return super.readAll(parentId, pageable, selfLink);
    }

    @Override
    public Optional<PgSoftDTOPagedResources<PgSoftDTOResource<XRefDTO>>> readAll(Predicate predicate, Pageable pageable, Function<Pageable, Link> selfLink) {
        return super.readAll(predicate, pageable, selfLink);
    }

    public Optional<PgSoftDTOResource<XRefDTO>> create(@Nullable Long parentId, @Nullable Long id) {
        XRefDTO dto = new XRefDTO();
        dto.setParentId(parentId);
        dto.setXrefId(id);
        return super.save(this.getMapper().toNewDBO(dto)).map(this.getMapper()::toDTO);
    }

    public Optional<PgSoftDTOResource<XRefDTO>> update(@Nullable Long id, @Nullable XRefDTO updatedObject) {
        return super.update(super.findById(id).orElseThrow(NotFoundException::new), updatedObject);
    }

    public void deleteById(@Nullable Long parentId, @Nullable Long id) {
        super.delete(parentId, id);
    }
}
