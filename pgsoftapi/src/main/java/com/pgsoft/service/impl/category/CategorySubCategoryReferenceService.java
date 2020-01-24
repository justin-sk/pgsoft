package com.pgsoft.service.impl.category;

import com.pgsoft.persistence.impl.jpa.dbo.Category;
import com.pgsoft.persistence.impl.jpa.dbo.CategorySubCategoryReference;
import com.pgsoft.persistence.impl.jpa.querydsl.CategorySubCategoryReferenceQueryDsl;
import com.pgsoft.persistence.impl.jpa.repository.CategorySubCategoryReferenceRepository;
import com.pgsoft.service.PgSoftChildServiceAdapter;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.category.mapper.CategorySubCategoryReferenceMapper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CategorySubCategoryReferenceService extends PgSoftChildServiceAdapter<CategorySubCategoryReference, XRefDTO, Category> {
    protected CategorySubCategoryReferenceService(CategorySubCategoryReferenceMapper mapper
            , CategorySubCategoryReferenceRepository repository
            , CategorySubCategoryReferenceQueryDsl searchRepository) {
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
