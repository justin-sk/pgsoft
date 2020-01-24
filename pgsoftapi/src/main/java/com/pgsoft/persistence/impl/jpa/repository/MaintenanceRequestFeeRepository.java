package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.MaintenanceRequestFee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaintenanceRequestFeeRepository extends IPgSoftChildJpaRepository<MaintenanceRequestFee> {
    @Override
    @Query("select obj from MaintenanceRequestFee obj where obj.maintenanceRequest.id= :parentId")
    Page<MaintenanceRequestFee> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from MaintenanceRequestFee obj where obj.maintenanceRequest.id= :parentId and obj.maintenanceRequest.id= :id")
    Optional<MaintenanceRequestFee> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
