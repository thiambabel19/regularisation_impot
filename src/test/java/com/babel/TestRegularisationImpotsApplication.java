package com.babel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestRegularisationImpotsApplication {

	public static void main(String[] args) {
		SpringApplication.from(RegularisationImpotsApplication::main).with(TestRegularisationImpotsApplication.class).run(args);
	}

}
