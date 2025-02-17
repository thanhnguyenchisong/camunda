//package com.ncst.db;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//
//import javax.sql.DataSource;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//
//@Configuration
//@EnableJpaRepositories(
//    basePackages = "com.ncst.entity",  // Package for secondary DB repositories
//    entityManagerFactoryRef = "secondaryEntityManagerFactory",
//    transactionManagerRef = "secondaryTransactionManager"
//)
//public class SecondaryDataSourceConfig {
//
//  @Bean
//  public DataSource secondaryDataSource(@Value("${spring.datasource.secondary.url}") String url) {
//    return DataSourceBuilder.create()
//        .url(url)
//        .username("sa")
//        .driverClassName("org.h2.Driver")
//        .build();
//  }
//
//  @Bean
//  public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
//      @Qualifier("secondaryDataSource") DataSource dataSource) {
//    LocalContainerEntityManagerFactoryBean f = builder
//        .dataSource(dataSource)
//        .packages("com.ncst.entity")  // Package where secondary DB entities are
//        .persistenceUnit("secondary")
//        .build();
//    System.out.println("Thanh");
//    return f;
//  }
//
//  @Bean
//  public JpaTransactionManager secondaryTransactionManager(@Qualifier("secondaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//    return new JpaTransactionManager(entityManagerFactory);
//  }
//}
