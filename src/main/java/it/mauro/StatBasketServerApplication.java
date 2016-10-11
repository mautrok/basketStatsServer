package it.mauro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import statBasketServer.TeamRestController;

@SpringBootApplication
@ComponentScan(basePackages = {"statBasketJPA","statBasketServer"})
@EnableAutoConfiguration
public class StatBasketServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatBasketServerApplication.class, args);
	}
}
