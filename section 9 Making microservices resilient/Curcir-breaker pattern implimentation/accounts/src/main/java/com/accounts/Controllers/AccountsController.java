package com.accounts.Controllers;

import com.accounts.Clients.CardsFeignClient;
import com.accounts.Clients.LoansFeignClient;
import com.accounts.Configuration.TestShowingAllConfigData;
import com.accounts.Model.*;
import com.accounts.Repositories.AccountsRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/getAllDetails")
//    Without fallback mechanism
//    @CircuitBreaker(name="detailsForCustomerSupportApp") //implemented the circuit breaker

//    To implement fallback mechanism
    @CircuitBreaker(name ="detailsForCustomerSupportApp",fallbackMethod = "myCustomerDetailsFallBack")
    public CustomerDetails getData(@RequestBody Customer customer){
        Accounts accounts = accountsRepository.findAccountsByCustomerId(customer.getCustomerId());
        List<Cards> cards = cardsFeignClient.getCardsDetails(customer);
        List<Loans> loans = loansFeignClient.getAllloans(customer);
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(accounts);
        customerDetails.setCards(cards);
        customerDetails.setLoans(loans);
        return customerDetails;
    }

    //Fall back method what will be call in the case of circuit-breaker fallback
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
}
