package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.UserBed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBedRepository extends IPgSoftChildJpaRepository<UserBed> {
    @Override
    @Query("select obj from UserBed obj where obj.user.id= :parentId")
    Page<UserBed> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from UserBed obj where obj.user.id= :parentId and obj.bed.id= :id")
    Optional<UserBed> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
