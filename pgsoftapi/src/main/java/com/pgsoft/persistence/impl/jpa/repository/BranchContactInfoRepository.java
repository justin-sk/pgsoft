package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.BranchContactInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchContactInfoRepository extends IPgSoftChildJpaRepository<BranchContactInfo> {
    @Override
    @Query("select obj from BranchContactInfo obj where obj.branch.id= :parentId")
    Page<BranchContactInfo> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from BranchContactInfo obj where obj.branch.id= :parentId and obj.id= :id")
    Optional<BranchContactInfo> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
