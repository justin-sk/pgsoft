package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.UserPwd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPwdRepository extends IPgSoftChildJpaRepository<UserPwd> {
    @Override
    @Query("select obj from UserPwd obj where obj.user.id= :parentId")
    Page<UserPwd> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from UserPwd obj where obj.user.id= :parentId and obj.user.id= :id")
    Optional<UserPwd> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);

    @Query("select obj from UserPwd obj where obj.user.email= :email")
    Optional<UserPwd> findByUserName(@Param("email") String email);
}
