package com.mojro.vertx;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mojro.vertx.verticles.ServerVerticle;
import com.mojro.vertx.verticles.BookRecipientVerticle;

import io.vertx.core.Vertx;

@SpringBootApplication
@Configuration
@EnableJpaRepositories("com.mojro.vertx.repository")
@EntityScan("com.mojro.vertx.entity")
@ComponentScan(basePackages = { "com.mojro.vertx.*" })

public class VertxSpringApplication {

	@Autowired
	private ServerVerticle serverVerticle;

	@Autowired
	private BookRecipientVerticle serviceVerticle;

	public static void main(String[] args) {
		SpringApplication.run(VertxSpringApplication.class, args);

	}

	@PostConstruct
	public void deployVerticle() {
		System.out.println("Inside deployVerticle method");
		final Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(serverVerticle);
		System.out.println("Server verticle deployed");
		vertx.deployVerticle(serviceVerticle);
		System.out.println("Verticles deployed");
	}

}
