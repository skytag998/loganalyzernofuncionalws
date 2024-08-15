package com.walmart.services.loganalyzer.LogAnalizerWalmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class LogAnalizerWalmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogAnalizerWalmartApplication.class, args);
	}

	protected SpringApplicationBuilder configure (SpringApplicationBuilder application){
		return application.sources(LogAnalizerWalmartApplication.class);
	}

}
