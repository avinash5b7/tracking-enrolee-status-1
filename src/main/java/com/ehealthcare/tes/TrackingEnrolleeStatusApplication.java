package com.ehealthcare.tes;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrackingEnrolleeStatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackingEnrolleeStatusApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {

		return new OpenAPI()
			.info(new Info()
						.title("Tracking Enrollee Status")
						.version("1.0")
						.description("Tracking the status of enrollees in a health care program")
						.termsOfService("http://ehealthcare.com/terms/")
						.license(new License().name("GNU 2.0").url("http://ehealthcare.com")));
	}

}
