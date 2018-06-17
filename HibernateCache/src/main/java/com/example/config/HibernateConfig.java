package com.example.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author sushil.kumar
 *
 */
@EnableCaching
@Configuration
@ConfigurationProperties
@EnableTransactionManagement
public class HibernateConfig {
	
	 @Value("${datasource.driver}")
	  private String dbDriver;
	  @Value("${datasource.password}")
	  private String dbPassword;
	  @Value("${datasource.url}")
	  private String dbUrl;
	  @Value("${datasource.username}")
	  private String dbUsername;
	  @Value("${hibernate.show_sql}")
	  private String showSql;
	  @Value("${hibernate.hbm2ddl.auto}")
	  private String hibernateDdlAuto;
	  @Value("${entitymanager.packagesToScan}")
	  private String packagesToScan;
	  @Value("${hibernate.dialect}")
	  private String hibernateDialect;
	  @Value("${hibernate.c3p0.min_size}")
	  private String poolMinSize;
	  @Value("${hibernate.c3p0.max_size}")
	  private String poolMaxSize;
	  @Value("${hibernate.c3p0.timeout}")
	  private String poolTimeOut;
	  @Value("${hibernate.c3p0.max_statements}")
	  private String poolMaxStatements;
	  @Value("${hibernate.cache.use_second_level_cache}")
	  private String hibernateSecondLevelCache;
	  @Value("${hibernate.cache.region.factory_class}")
	  private String hibernateCacheRegionfactory;
	  @Value("${hibernate.cache.use_query_cache}")
	  private String hibernateQueryLevelCache;
	  @Value("${hibernate.cache.region_prefix}")
	  private String hibernateCacheRegionPrefix;
	  
	  
	  @Autowired
	  @Bean
	  public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(dbDriver);
	    dataSource.setUrl(dbUrl);
	    dataSource.setUsername(dbUsername);
	    dataSource.setPassword(dbPassword);
	    return dataSource;
	  }

	  @Bean
	  public LocalSessionFactoryBean sessionFactory() {
	    LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
	    sessionFactoryBean.setDataSource(dataSource());
	    Properties hibernateProperties = new Properties();
	    hibernateProperties.put("hibernate.dialect", hibernateDialect);
	    hibernateProperties.put("hibernate.show_sql", showSql);
	    hibernateProperties.put("hibernate.hbm2ddl.auto", hibernateDdlAuto);
	    hibernateProperties.put("hibernate.c3p0.min_size", poolMinSize);
	    hibernateProperties.put("hibernate.c3p0.max_size", poolMaxSize);
	    hibernateProperties.put("hibernate.c3p0.timeout",poolTimeOut);
	    hibernateProperties.put("hibernate.c3p0.max_statements",poolMaxStatements);
	    hibernateProperties.put("hibernate.cache.region.factory_class",hibernateCacheRegionfactory);
	    hibernateProperties.put("hibernate.cache.use_query_cache",hibernateQueryLevelCache);
	    hibernateProperties.put("hibernate.cache.use_second_level_cache", hibernateSecondLevelCache);
	    hibernateProperties.put("hibernate.cache.region_prefix", hibernateCacheRegionPrefix);
	    sessionFactoryBean.setHibernateProperties(hibernateProperties);
	    sessionFactoryBean.setPackagesToScan(packagesToScan);
	    return sessionFactoryBean;
	  }

	  @Bean
	  public HibernateTransactionManager transactionManager() {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	    transactionManager.setSessionFactory(sessionFactory().getObject());
	    return transactionManager;
	  }
//	  @Bean
//	    public CacheManager cacheManager() {
//	        //A EhCache based Cache manager
//	        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
//		   
//	    }
//	 
//	    @Bean
//	    public EhCacheManagerFactoryBean ehCacheCacheManager() {
//	        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
//	        factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
//	        factory.setShared(true);
//	        return factory;
//	    }
}
