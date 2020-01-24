package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.UserExitDtls;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserExitDtlsRepository extends IPgSoftChildJpaRepository<UserExitDtls> {
    @Override
    @Query("select obj from UserExitDtls obj where obj.user.id= :parentId")
    Page<UserExitDtls> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from UserExitDtls obj where obj.user.id= :parentId and obj.user.id= :id")
    Optional<UserExitDtls> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
