package com.authoriza.api.shared.persistence.hibernate;

import com.authoriza.shared.infrastructure.hibernate.HibernateConfigurationFactory;
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
    private static final String MODULE_NAME = "api";

    private final HibernateConfigurationFactory factory;
    private final ApiConfigurationProperties properties;

    public ApiHibernateConfiguration(
            HibernateConfigurationFactory factory,
            ApiConfigurationProperties properties
    ) {
        this.factory = factory;
        this.properties = properties;
    }

    @Bean("authoriza-api-transaction_manager")
    public PlatformTransactionManager hibernateTransactionManager() {
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean("authoriza-api-session_factory")
    public LocalSessionFactoryBean sessionFactory() {
        return factory.sessionFactory(dataSource(), CONTEXT_NAME, PACKAGE_NAME, MODULE_NAME);
    }

    @Bean("authoriza-api-data_source")
    public DataSource dataSource() {
        return factory.dataSource(
                properties.getDriverClassName(),
                properties.getUrl(),
                properties.getUsername(),
                properties.getPassword()
        );
    }
}
