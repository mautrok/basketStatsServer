package it.mauro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import statBasketServer.TeamRestController;

@SpringBootApplication
@EnableJpaRepositories("statBasketJPA")
@ComponentScan(basePackages = {"statBasketServer"})
@EnableAutoConfiguration
public class StatBasketServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatBasketServerApplication.class, args);
	}
}
