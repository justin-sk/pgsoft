package com.pgsoft.service.impl.menu;

import com.pgsoft.persistence.impl.jpa.dbo.Role;
import com.pgsoft.persistence.impl.jpa.dbo.RoleMenuXref;
import com.pgsoft.persistence.impl.jpa.querydsl.RoleMenuXrefQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.RoleMenuXrefRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.impl.menu.mapper.RoleMenuMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Function;

@Service
public class RoleMenuService extends PgSoftChildServiceAdapter<RoleMenuXref, XRefDTO, Role> {
    protected RoleMenuService(RoleMenuMapper mapper
            , RoleMenuXrefRepository repository
            , RoleMenuXrefQueryDsl searchRepository) {
        super(mapper, repository, searchRepository);
    }

    public Optional<PgSoftDTOResource<XRefDTO>> read(@Nullable Long parentId, @Nullable Long id) {
        return super.read(parentId, id);
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

    public void deleteById(@Nullable Long parentId, @Nullable Long id) {
        super.delete(parentId, id);
    }
}
