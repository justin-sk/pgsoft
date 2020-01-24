package com.pgsoft.persistence.impl.jpa.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class
        ,basePackages ={"com.pgsoft.persistence.impl.jpa"}
        ,excludeFilters = @ComponentScan.Filter(type= FilterType.REGEX,pattern = {"com\\.pgsoft\\.persistence\\.jpa\\.querydsl\\..*"}))
@Configuration
public class EnversConfiguration {
    /*public static class AuditRevisionListener implements RevisionListener {
        @Override
        public void newRevision(Object o){
            final Authentication
        }
    }*/
}
