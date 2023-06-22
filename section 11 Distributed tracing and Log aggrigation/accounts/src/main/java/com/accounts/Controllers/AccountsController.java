package com.accounts.Controllers;

import com.accounts.Clients.CardsFeignClient;
import com.accounts.Clients.LoansFeignClient;
import com.accounts.Configuration.TestShowingAllConfigData;
import com.accounts.Model.*;
import com.accounts.Repositories.AccountsRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class AccountsController {
    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private TestShowingAllConfigData testShowingAllConfigData;
    @PostMapping("/myAccount")
    public Accounts getAccountDetails(@RequestBody Customer customer){
        Accounts accounts = accountsRepository.findAccountsByCustomerId(customer.getCustomerId());
        return accounts;
    }

    @GetMapping("/test")
    public String getTest(){
        return "test";
    }

    @GetMapping("/accounts/details")
    public String getConfigurationValue(){
        return testShowingAllConfigData.getMsg();
    }



//    Calling microservices using feign
    @Autowired
    CardsFeignClient cardsFeignClient;


    @Autowired
    LoansFeignClient loansFeignClient;


//    ####################################################################################################################
//    Logger object creation
    private static final Logger Logger = (java.util.logging.Logger) LoggerFactory.getLogger(AccountsController.class);
    @GetMapping("/getAllDetails")
//  to implement retry pattern
    @Retry(name = "retryForCustomerDetails",fallbackMethod = "myCustomerDetailsFallBack")
    public CustomerDetails getData(@RequestBody Customer customer){
//        Logging the data
        Logger.info("Inside the accounts service");

        Accounts accounts = accountsRepository.findAccountsByCustomerId(customer.getCustomerId());
        List<Cards> cards = cardsFeignClient.getCardsDetails(customer);
        List<Loans> loans = loansFeignClient.getAllloans(customer);
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(accounts);
        customerDetails.setCards(cards);
        customerDetails.setLoans(loans);


//        Logging the data
        Logger.info("Data is : "+customerDetails.toString());
        return customerDetails;
    }

    //Fall back method what will be call in the case of circuit-breaker/retry fallback
//    Throwable --> it's mandatory to have. to accept the exception my fallback
    private CustomerDetails myCustomerDetailsFallBack(Customer customer,Throwable t){

//        We can send anything in fallback --> normal string also
        Accounts accounts = accountsRepository.findAccountsByCustomerId(customer.getCustomerId());
        List<Loans> loans = loansFeignClient.getAllloans(customer);
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(accounts);
        customerDetails.setLoans(loans);
        return customerDetails;
    }



//   ####################################################################################################################
//    Implimenting the rate limiter Pattern
    @GetMapping("/sayHello")
    @RateLimiter(name = "sayHello",fallbackMethod = "sayHelloFallback")
    public String sayHello()
    {
        return "Hello World, welcome to the world";
    }

    private String sayHelloFallback(Throwable t){
        return "fallback mechanism is running";
    }
}
