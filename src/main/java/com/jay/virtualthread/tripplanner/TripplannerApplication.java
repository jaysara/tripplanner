package com.jay.virtualthread.tripplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TripplannerApplication {

	public static void main(String[] args) {
        System.out.println("Java version : "+System.getenv("java.version"));
        SpringApplication.run(TripplannerApplication.class, args);
	}

}
