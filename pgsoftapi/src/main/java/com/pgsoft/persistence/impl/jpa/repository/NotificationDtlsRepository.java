package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.NotificationDtls;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationDtlsRepository extends IPgSoftChildJpaRepository<NotificationDtls> {
    @Override
    @Query("select obj from NotificationDtls obj where obj.notification.id= :parentId")
    Page<NotificationDtls> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from NotificationDtls obj where obj.notification.id= :parentId and obj.id= :id")
    Optional<NotificationDtls> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
