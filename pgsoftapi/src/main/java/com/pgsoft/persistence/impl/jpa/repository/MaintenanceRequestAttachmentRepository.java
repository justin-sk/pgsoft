package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequestAttachment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaintenanceRequestAttachmentRepository extends IPgSoftChildJpaRepository<MaintenanceRequestAttachment> {
    @Override
    @Query("select obj from MaintenanceRequestAttachment obj where obj.maintenanceRequest.id= :parentId")
    Page<MaintenanceRequestAttachment> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from MaintenanceRequestAttachment obj where obj.maintenanceRequest.id= :parentId and obj.id= :id")
    Optional<MaintenanceRequestAttachment> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
