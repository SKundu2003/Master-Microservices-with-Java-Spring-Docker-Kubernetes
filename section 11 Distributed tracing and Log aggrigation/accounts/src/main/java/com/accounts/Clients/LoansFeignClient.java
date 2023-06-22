package com.accounts.Clients;

import com.accounts.Model.Customer;
import com.accounts.Model.Loans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("loans")
public interface LoansFeignClient {

    @RequestMapping(method = RequestMethod.POST,value = "myLoans",consumes = "application/json")
    List<Loans> getAllloans(@RequestBody Customer customer);

}
