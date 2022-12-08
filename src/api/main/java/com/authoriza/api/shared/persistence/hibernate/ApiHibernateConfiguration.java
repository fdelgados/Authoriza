package com.authoriza.api.shared.persistence.hibernate;

import com.authoriza.shared.infrastructure.hibernate.HibernateConfigurationFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class ApiHibernateConfiguration {
    private static final String PACKAGE_NAME = "authoriza";
    private static final String CONTEXT_NAME = "api";

    private final HibernateConfigurationFactory factory;

    @Value("${spring.datasource.driver-class-name}")
    private String databaseDriver;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Value("${spring.datasource.password}")
    private String databasePassword;

    public ApiHibernateConfiguration(HibernateConfigurationFactory factory) {
        this.factory = factory;
    }

    @Bean("authoriza-api-transaction_manager")
    public PlatformTransactionManager hibernateTransactionManager() {
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean("authoriza-api-session_factory")
    public LocalSessionFactoryBean sessionFactory() {
        return factory.sessionFactory(dataSource(), CONTEXT_NAME, PACKAGE_NAME);
    }

    @Bean("authoriza-api-data_source")
    public DataSource dataSource() {
        return factory.dataSource(databaseDriver, databaseUrl, databaseUsername, databasePassword);
    }
}
