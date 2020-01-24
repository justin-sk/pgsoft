package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.ExpenditureAttachments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenditureAttachmentsRepository extends IPgSoftChildJpaRepository<ExpenditureAttachments> {
    @Override
    @Query("select obj from ExpenditureAttachments obj where obj.expenditure.id= :parentId")
    Page<ExpenditureAttachments> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from ExpenditureAttachments obj where obj.expenditure.id= :parentId and obj.id= :id")
    Optional<ExpenditureAttachments> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);

}
