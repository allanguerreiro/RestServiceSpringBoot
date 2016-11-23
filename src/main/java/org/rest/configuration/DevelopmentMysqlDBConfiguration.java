package org.rest.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * Created by allan on 22/11/16.
 */
@Profile("dev")
@Configuration
@EnableJpaRepositories(basePackages = "org.rest.persistence")
public class DevelopmentMysqlDBConfiguration {

    private final Logger log = LoggerFactory.getLogger(DevelopmentMysqlDBConfiguration.class.getName());

    @Bean
    @ConfigurationProperties(prefix = "datasource.dev")
    public DataSource dataSource() {
        log.info("########### Datasource Development ###########");
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        log.info("########### LocalContainerEntityManagerFactoryBean Development ###########");
        return LocalEntityManagerFactory.entityManagerFactory(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(transactionManager());
    }
}