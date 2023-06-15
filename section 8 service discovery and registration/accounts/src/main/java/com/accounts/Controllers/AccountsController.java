package com.accounts.Controllers;

import com.accounts.Clients.CardsFeignClient;
import com.accounts.Clients.LoansFeignClient;
import com.accounts.Configuration.TestShowingAllConfigData;
import com.accounts.Model.*;
import com.accounts.Repositories.AccountsRepository;
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
}
