package com.lee.landon.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/** @author Lee97 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:database/jdbc.properties")
public class ApplicationConfig {

  @Value(value = "${jdbc.driverClassName}")
  String driverClassName;

  @Value(value = "${jdbc.url}")
  String url;

  @Value(value = "${jdbc.username}")
  String username;

  @Value(value = "${jdbc.password}")
  String password;



  @Bean
  public BasicDataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
//    dataSource.setMaxActive(2);
//    dataSource.setMinIdle(1);
    return dataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource());
  }

  @Bean
  public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }

//  @Bean
//  public SimpleMappingExceptionResolver exceptionResolver() {
//    SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
//    Properties properties = new Properties();
//    properties.setProperty(RuntimeException.class.getName(), "error/runtime");
//    exceptionResolver.setExceptionMappings(properties);
//    exceptionResolver.setDefaultErrorView("error/default");
//    return exceptionResolver;
//  }

}
