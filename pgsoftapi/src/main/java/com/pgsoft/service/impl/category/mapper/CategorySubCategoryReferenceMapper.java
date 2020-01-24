package com.pgsoft.service.impl.category.mapper;

import com.pgsoft.persistence.impl.jpa.dbo.Category;
import com.pgsoft.persistence.impl.jpa.dbo.CategorySubCategoryReference;
import com.pgsoft.persistence.impl.jpa.repository.CategoryRepository;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.type.CategoryService;
import com.pgsoft.service.impl.type.SubCategoryService;
import com.pgsoft.service.mapper.PgSoftChildMapperAdapter;
import com.pgsoft.web.rest.v1.pgsoft.category.CategorySubCategoryReferenceResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CategorySubCategoryReferenceMapper extends PgSoftChildMapperAdapter<CategorySubCategoryReference, XRefDTO, Category> {
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    public CategorySubCategoryReferenceMapper(CategoryService categoryService
            , SubCategoryService subCategoryService
            , CategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }

    @Override
    public void populateChildDBO(@NotNull CategorySubCategoryReference dbo, @NotNull XRefDTO dto) {
        dbo.setCategory(categoryService.findById(dto.getParentId()).orElseThrow(NotFoundException::new));
        dbo.setSubCategory(subCategoryService.findById(dto.getXrefId()).orElseThrow(NotFoundException::new));
    }

    @Override
    protected XRefDTO toDTOImpl(@NotNull CategorySubCategoryReference dbo) {
        return new XRefDTO();
    }

    @Override
    protected void populateLinksImpl(List<Link> links, CategorySubCategoryReference dbo) {

    }

    @Override
    protected Link getSelfLink(CategorySubCategoryReference dbo) {
        return linkTo(methodOn(CategorySubCategoryReferenceResource.class).readAll(dbo.getId(), null, null)).withSelfRel();
    }

    @Override
    protected void embeddedResources(CategorySubCategoryReference dbo, @NotNull PgSoftDTOResource<XRefDTO> dto) {
        super.embeddedResources(dbo, dto);
        dto.embedResource("category", categoryService.getMapper().toDTO(dbo.getCategory()));
        dto.embedResource("subCategory", subCategoryService.getMapper().toDTO(dbo.getSubCategory()));
    }
}
