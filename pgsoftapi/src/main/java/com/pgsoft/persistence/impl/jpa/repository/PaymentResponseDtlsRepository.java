package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.PaymentResponseDtls;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentResponseDtlsRepository extends IPgSoftChildJpaRepository<PaymentResponseDtls> {
    @Override
    @Query("select obj from PaymentResponseDtls obj where obj.paymentDtls.id= :parentId")
    Page<PaymentResponseDtls> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from PaymentResponseDtls obj where obj.paymentDtls.id= :parentId and obj.paymentDtls.id= :id")
    Optional<PaymentResponseDtls> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
