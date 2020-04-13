package com.pluralsight.conferencedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.pluralsight.conferencedemo.configuration.ApplicationProperties;


@SpringBootApplication
@EnableConfigurationProperties({  ApplicationProperties.class })
public class ConferenceDemoApplication extends SpringBootServletInitializer  { //This is needed to build the application as a WAR file

	public static void main(String[] args) {
		SpringApplication.run(ConferenceDemoApplication.class, args);
	}

}
