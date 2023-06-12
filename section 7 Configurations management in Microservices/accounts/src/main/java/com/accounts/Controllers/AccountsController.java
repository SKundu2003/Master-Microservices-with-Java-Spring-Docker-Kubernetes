package com.accounts.Controllers;

import com.accounts.Configuration.TestShowingAllConfigData;
import com.accounts.Model.Accounts;
import com.accounts.Model.Customer;
import com.accounts.Repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
