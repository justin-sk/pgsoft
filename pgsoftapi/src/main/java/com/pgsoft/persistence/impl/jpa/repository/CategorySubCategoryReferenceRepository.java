package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.CategorySubCategoryReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorySubCategoryReferenceRepository extends IPgSoftChildJpaRepository<CategorySubCategoryReference> {
    @Override
    @Query("select obj from CategorySubCategoryReference obj where obj.category.id= :parentId")
    Page<CategorySubCategoryReference> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from CategorySubCategoryReference obj where obj.category.id= :parentId and obj.subCategory.id= :id")
    Optional<CategorySubCategoryReference> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
