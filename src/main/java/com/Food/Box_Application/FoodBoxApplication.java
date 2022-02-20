package com.Food.Box_Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories(basePackages = { "com.foodboxApp.Repository" })
@EntityScan(basePackages = { "com.foodboxApp.Entity" })
@ComponentScan(basePackages = { "com.Foodbox_App.*,com.foodboxApp.Service" })
@SpringBootApplication
public class FoodBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodBoxApplication.class, args);
	}

}
