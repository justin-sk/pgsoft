package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.UserBranch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBranchRepository extends IPgSoftChildJpaRepository<UserBranch> {
    @Override
    @Query("select obj from UserBranch obj where obj.user.id= :parentId")
    Page<UserBranch> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from UserBranch obj where obj.user.id= :parentId and obj.branch.id= :id")
    Optional<UserBranch> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
