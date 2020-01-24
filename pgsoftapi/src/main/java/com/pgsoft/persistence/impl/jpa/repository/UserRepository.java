package com.pgsoft.persistence.impl.jpa.repository;

import com.pgsoft.persistence.impl.jpa.dbo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IPgSoftJpaRepository<User> {
}
