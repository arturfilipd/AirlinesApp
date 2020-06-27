package com.AirlinesApp;

import com.AirlinesApp.Configuration.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class AirlinesAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlinesAppApplication.class, args);
	}

}
