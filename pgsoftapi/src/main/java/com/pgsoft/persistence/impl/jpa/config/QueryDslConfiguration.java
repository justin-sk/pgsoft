package com.pgsoft.persistence.impl.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.pgsoft.persistence.impl.jpa.querydsl"})
@Configuration
public class QueryDslConfiguration {
}
