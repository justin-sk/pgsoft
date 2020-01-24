package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.UserTc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTcRepository extends IPgSoftChildJpaRepository<UserTc> {
    @Override
    @Query("select obj from UserTc obj where obj.user.id= :parentId")
    Page<UserTc> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from UserTc obj where obj.user.id= :parentId and obj.user.id= :id")
    Optional<UserTc> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
