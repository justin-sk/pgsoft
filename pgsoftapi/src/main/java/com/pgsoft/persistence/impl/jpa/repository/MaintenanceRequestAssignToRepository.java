package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequestAssignTo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaintenanceRequestAssignToRepository extends IPgSoftChildJpaRepository<MaintenanceRequestAssignTo> {
    @Override
    @Query("select obj from MaintenanceRequestAssignTo obj where obj.maintenanceRequest.id= :parentId")
    Page<MaintenanceRequestAssignTo> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from MaintenanceRequestAssignTo obj where obj.maintenanceRequest.id= :parentId and obj.user.id= :id")
    Optional<MaintenanceRequestAssignTo> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
