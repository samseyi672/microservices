package com.activedge.config;

import javax.sql.DataSource;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;

import com.activedge.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class DataSourceConfiguration
    {

        @Bean(destroyMethod = "close")
        @Primary
        @ConfigurationProperties(prefix = "spring.datasource")
        public DataSource dataSource()
        {
            return DataSourceBuilder.create().build();
        }


        @Bean(name = "customerInsightDataSource")
        @ConfigurationProperties(prefix = "spring.customerinsight-datasource")
        public DataSource customerInsightDataSource() {
            return DataSourceBuilder.create().build();
        }

        @Bean(name = "customerInsightJdbcTemplate")
        public JdbcTemplate customerInsightJdbcTemplate(@Qualifier("customerInsightDataSource") DataSource dataSource) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(dataSource);
            return jdbcTemplate;
        }

        @Bean(name = "ndprInfoPoolJdbcTemplate")
        public JdbcTemplate ndprInfoPoolJdbcTemplate(@Qualifier("ndprInfopoolDataSource") DataSource dataSource) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(dataSource);
            return jdbcTemplate;
        }

        @Bean(name = "ndprInfopoolDataSource")
        @ConfigurationProperties(prefix = "spring.ndpr-infopool-datasource")
        public DataSource ndprInfopoolDataSource() {
            return DataSourceBuilder.create().build();
        }

        @Bean(name = "duisDataSource")
        @ConfigurationProperties(prefix = "spring.duis-datasource")
        public DataSource duisDataSource() {
            return DataSourceBuilder.create().build();
        }

        @Bean(name = "duisJdbcTemplate")
        public JdbcTemplate duisJdbcTemplate(@Qualifier("duisDataSource") DataSource dataSource) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(dataSource);
            return jdbcTemplate;
        }

        @Bean
        @Primary
        public EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties jpaProperties, JpaVendorAdapter jpaVendorAdapter)
        {
            LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
            emf.setDataSource(dataSource);
            emf.setJpaVendorAdapter(jpaVendorAdapter);
            emf.setPackagesToScan(User.class.getPackage().getName());
            emf.setJpaProperties(jpaProperties);
            emf.afterPropertiesSet();
            return emf.getObject();
        }


        @Bean
        @ConfigurationProperties(prefix = "spring.jpa.properties")
        public Properties jpaProperties()
        {
            return new Properties();
        }


        @Bean
        public SessionFactory sessionFactory(EntityManagerFactory entityManagerFactory)
        {
            return entityManagerFactory.unwrap(SessionFactory.class);
        }


        @Bean(name="transactionManager")
        public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory)
        {
            return new HibernateTransactionManager(sessionFactory);
        }

    }

