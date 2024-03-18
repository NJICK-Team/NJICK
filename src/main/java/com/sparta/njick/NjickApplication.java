package com.sparta.njick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:db.properties", "classpath:jwt.properties"})
public class NjickApplication {

	public static void main(String[] args) {
		SpringApplication.run(NjickApplication.class, args);
	}

}
