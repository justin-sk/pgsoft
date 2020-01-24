package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.UserUpcomingPayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserUpcomingPaymentRepository extends IPgSoftChildJpaRepository<UserUpcomingPayment> {
    @Override
    @Query("select obj from UserUpcomingPayment obj where obj.user.id= :parentId")
    Page<UserUpcomingPayment> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from UserUpcomingPayment obj where obj.user.id= :parentId and obj.id= :id")
    Optional<UserUpcomingPayment> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
