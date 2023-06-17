package com.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@RefreshScope //express an endpoint named "/refresh" to import the changes in properties with out restarting

//Only use if Autowire is not working properly
//To scan all my beans present inside my other packages
//@ComponentScans({@ComponentScan("com.loan.Controllers")}) //tell the spring where to search for beans

//It will find all repository classes present inside a particular package
@EnableJpaRepositories("com.loans.Repositories")

//Telling SpringBoot to scan all entity model classes present inside the package
@EntityScan("com.loans.Model")
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
