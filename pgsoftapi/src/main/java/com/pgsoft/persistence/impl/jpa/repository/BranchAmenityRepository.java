package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.BranchAmenity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchAmenityRepository extends IPgSoftChildJpaRepository<BranchAmenity> {
    @Override
    @Query("select obj from BranchAmenity obj where obj.branch.id= :parentId")
    Page<BranchAmenity> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from BranchAmenity obj where obj.branch.id= :parentId and obj.amenity.id= :id")
    Optional<BranchAmenity> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
