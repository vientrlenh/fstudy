package com.vsoft.fstudy;

import org.springframework.boot.SpringApplication;

public class TestFstudyApplication {

	public static void main(String[] args) {
		SpringApplication.from(FstudyApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
