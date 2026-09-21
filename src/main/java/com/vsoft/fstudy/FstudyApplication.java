package com.vsoft.fstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan 
public class FstudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FstudyApplication.class, args);
	}

}
