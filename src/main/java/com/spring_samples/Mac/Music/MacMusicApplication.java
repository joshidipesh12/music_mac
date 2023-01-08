package com.spring_samples.Mac.Music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, ErrorMvcAutoConfiguration.class })
public class MacMusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MacMusicApplication.class, args);
	}

}
