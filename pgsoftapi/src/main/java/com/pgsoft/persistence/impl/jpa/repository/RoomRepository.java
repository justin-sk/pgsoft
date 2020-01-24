package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends IPgSoftChildJpaRepository<Room> {
    @Override
    @Query("select obj from Room obj where obj.branch.id= :parentId")
    Page<Room> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from Room obj where obj.branch.id= :parentId and obj.id= :id")
    Optional<Room> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
