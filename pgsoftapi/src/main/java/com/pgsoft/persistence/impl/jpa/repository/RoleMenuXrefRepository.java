package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.RoleMenuXref;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleMenuXrefRepository extends IPgSoftChildJpaRepository<RoleMenuXref> {
    @Override
    @Query("select obj from RoleMenuXref obj where obj.role.id= :parentId")
    Page<RoleMenuXref> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from RoleMenuXref obj where obj.role.id= :parentId and obj.menu.id= :id")
    Optional<RoleMenuXref> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
