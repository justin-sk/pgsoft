package com.pgsoft.web.rest.v1.pgsoft.category;

import com.pgsoft.persistence.impl.jpa.dbo.CategorySubCategoryReference;
import com.pgsoft.persistence.impl.jpa.dbo.QCategorySubCategoryReference;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.dto.XRefDTO;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.category.CategorySubCategoryReferenceService;
import com.pgsoft.service.impl.category.binding.CategorySubCategoryReferenceBinding;
import com.pgsoft.web.rest.v1.IPgSoftResource;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Api(tags = {"Category Sub-Category XRef"})
@RequestMapping("api/v1/category/{categoryId}/subcategory")
public class CategorySubCategoryReferenceResource implements IPgSoftResource {
    private final CategorySubCategoryReferenceService categorySubCategoryReferenceService;

    public CategorySubCategoryReferenceResource(CategorySubCategoryReferenceService categorySubCategoryReferenceService) {
        this.categorySubCategoryReferenceService = categorySubCategoryReferenceService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<XRefDTO>> readAll(@PathVariable("categoryId") Long categoryId
            , @QuerydslPredicate(root = CategorySubCategoryReference.class, bindings = CategorySubCategoryReferenceBinding.class) Predicate predicate
            , Pageable pageable) {
        BooleanBuilder where = new BooleanBuilder();
        QCategorySubCategoryReference qCategorySubCategoryReference = QCategorySubCategoryReference.categorySubCategoryReference;
        where.and(qCategorySubCategoryReference.category.id.eq(categoryId));
        where.and(predicate);
        return categorySubCategoryReferenceService.readAll(where
                , pageable
                , p -> linkTo(methodOn(this.getClass()).readAll(categoryId, predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{subCategoryId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("categoryId") Long categoryId, @PathVariable("subCategoryId") Long id) {
        categorySubCategoryReferenceService.deleteById(categoryId, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/{subCategoryId}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<XRefDTO> create(@PathVariable("categoryId") Long categoryId, @PathVariable("subCategoryId") Long id) {
        return categorySubCategoryReferenceService.create(categoryId, id).orElseThrow(NotFoundException::new);
    }
}
