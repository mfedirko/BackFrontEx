package com.nouhoun.springboot.jwt.integration;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@SpringBootApplication
@ImportResource({"classpath:applicationContext.xml"})
public class SpringbootJwtApplication {

	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
	    HibernateJpaSessionFactoryBean fact = new HibernateJpaSessionFactoryBean();
	    fact.setEntityManagerFactory(emf);
	    return fact;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJwtApplication.class, args);
	}
}
