package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends IPgSoftChildJpaRepository<Employee> {
    @Override
    @Query("select obj from Employee obj where obj.user.id= :parentId")
    Page<Employee> extractByParentId(@Param("parentId") Long parentId, Pageable pageable);

    @Override
    @Query("select obj from Employee obj where obj.user.id= :parentId and obj.id= :id")
    Optional<Employee> extractByParentIdAndId(@Param("parentId") Long parentId, @Param("id") Long id);
}
