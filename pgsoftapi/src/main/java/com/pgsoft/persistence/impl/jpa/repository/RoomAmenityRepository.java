package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.RoomAmenity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomAmenityRepository extends IPgSoftChildJpaRepository<RoomAmenity> {
    @Override
    @Query("select obj from RoomAmenity obj where obj.room.id= :parentId")
    Page<RoomAmenity> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from RoomAmenity obj where obj.room.id= :parentId and obj.amenity.id= :id")
    Optional<RoomAmenity> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
