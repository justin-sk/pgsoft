package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.UserRent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRentRepository extends IPgSoftChildJpaRepository<UserRent> {
    @Override
    @Query("select obj from UserRent obj where obj.user.id= :parentId")
    Page<UserRent> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from UserRent obj where obj.user.id= :parentId and obj.user.id= :id")
    Optional<UserRent> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
