package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;


@SpringBootApplication
@ComponentScan(basePackages="com.example")
@EnableAutoConfiguration
@EnableCaching
public class HibernateCacheApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HibernateCacheApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HibernateCacheApplication.class, args);
}
	
	 @Bean
	    public CacheManager cacheManager() {
	        //A EhCache based Cache manager
	 
	        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
		   
	    }
	 
	    @Bean
	    public EhCacheManagerFactoryBean ehCacheCacheManager() {
	        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
	        factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
	        factory.setShared(true);
	        return factory;
	    }
	
}
