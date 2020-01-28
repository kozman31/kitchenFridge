package com.thekitchenfridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class KitchenfridgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KitchenfridgeApplication.class, args);
	}

}

