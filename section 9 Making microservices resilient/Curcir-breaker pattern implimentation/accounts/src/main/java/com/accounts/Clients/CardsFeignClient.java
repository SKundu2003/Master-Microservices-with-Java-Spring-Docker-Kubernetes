package com.accounts.Clients;

import com.accounts.Model.Cards;
import com.accounts.Model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("cards") //name that registered inside the eureka server
public interface CardsFeignClient {
    @RequestMapping(method = RequestMethod.POST,value = "myCards",consumes = "application/json")
    List<Cards> getCardsDetails(@RequestBody Customer customer);
}
