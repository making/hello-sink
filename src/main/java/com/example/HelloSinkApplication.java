package com.example;

import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloSinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSinkApplication.class, args);
	}

	@Bean
	public Consumer<Tweet> tweetPrinter() {
		return tweet -> System.out.println("Received " + tweet.text());
	}

	record Tweet(String text) {
	}
}