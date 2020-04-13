package com.pluralsight.conferencedemo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Patient Batch Loader.
 * <p>
 * Properties are configured in the application.yml file.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

	private final Batch batch = new Batch();
	
	public Batch getBatch() {
		return this.batch;
	}
	
	public static class Batch {
		private String inputPath = "D:\\home\\pluralsight_workspace_sts\\conference-demo-spring-boot\\src\\main\\resources\\data";
		
		public String getInputPath() {
			return this.inputPath;
		}

		public void setInputPath( String inputPath) {
			this.inputPath = inputPath;
		}
	}
}
