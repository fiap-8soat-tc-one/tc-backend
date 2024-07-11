package com.fiap.tc;

import com.fiap.tc.config.property.OriginApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(OriginApiProperty.class)
public class TcBackendApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(TcBackendApiApplication.class, args);
	}
	
}
