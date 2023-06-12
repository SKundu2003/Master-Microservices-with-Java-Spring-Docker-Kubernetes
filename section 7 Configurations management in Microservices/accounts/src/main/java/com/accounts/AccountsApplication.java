package com.accounts;

import com.accounts.Configuration.TestShowingAllConfigData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

//Only use if Autowire is not working properly
//To scan all my beans present inside my other packages
//@ComponentScans({@ComponentScan("com.accounts.Controllers")}) //tell the spring where to search for beans

//It will find all repository classes present inside a particular package
@EnableJpaRepositories("com.accounts.Repositories")

//Telling SpringBoot to scan all entity model classes present inside the package
@EntityScan("com.accounts.Model")
public class AccountsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
