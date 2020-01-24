package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends IPgSoftChildJpaRepository<UserRole> {
    @Override
    @Query("select obj from UserRole obj where obj.user.id= :parentId")
    Page<UserRole> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from UserRole obj where obj.user.id= :parentId and obj.id= :id")
    Optional<UserRole> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
