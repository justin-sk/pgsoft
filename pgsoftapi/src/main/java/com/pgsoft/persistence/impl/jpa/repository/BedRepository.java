package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.Bed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BedRepository extends IPgSoftChildJpaRepository<Bed> {
    @Override
    @Query("select obj from Bed obj where obj.room.id= :parentId")
    Page<Bed> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from Bed obj where obj.room.id= :parentId and obj.id= :id")
    Optional<Bed> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
