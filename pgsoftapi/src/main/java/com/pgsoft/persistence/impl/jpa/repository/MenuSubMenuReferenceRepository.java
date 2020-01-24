package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.MenuSubMenuReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuSubMenuReferenceRepository extends IPgSoftChildJpaRepository<MenuSubMenuReference> {
    @Override
    @Query("select obj from MenuSubMenuReference obj where obj.menu.id= :parentId")
    Page<MenuSubMenuReference> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from MenuSubMenuReference obj where obj.menu.id= :parentId and obj.subMenu.id= :id")
    Optional<MenuSubMenuReference> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
