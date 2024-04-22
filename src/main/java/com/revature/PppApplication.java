package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.*;
import org.springframework.boot.autoconfigure.domain.*;

@SpringBootApplication
@EnableJpaRepositories("com.revature.daos")
@ComponentScan("come.revature")
@EntityScan("com.revature.daos")
public class PppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PppApplication.class, args);
	}

}
