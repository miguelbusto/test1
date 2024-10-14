package com.mab.aws.sdk.TestS3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestS3Application {

	public static void main(String[] args) {
		System.setProperty("aws.java.v1.disableDeprecationAnnouncement", "true");
		SpringApplication.run(TestS3Application.class, args);
	}

}


