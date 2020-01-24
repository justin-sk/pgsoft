package com.pgsoft.persistence.impl.jpa.querydsl;

import com.pgsoft.persistence.impl.jpa.dbo.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeQueryDsl extends IPgSoftQueryDsl<Employee> {
}
