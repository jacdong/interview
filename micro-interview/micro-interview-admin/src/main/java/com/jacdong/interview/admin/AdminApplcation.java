package com.jacdong.interview.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AdminApplcation {
	public static void main(String[] args) {
		SpringApplication.run(AdminApplcation.class, args);
	}
}
