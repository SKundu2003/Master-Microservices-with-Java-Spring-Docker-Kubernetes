/**
 * 
 */
package com.eazybytes.cards.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.cards.model.Cards;
import com.eazybytes.cards.model.Customer;
import com.eazybytes.cards.repository.CardsRepository;

/**
 * @author Eazy Bytes
 *
 */

@RestController
public class CardsController {

	@Autowired
	private CardsRepository cardsRepository;

//	Implementing the logger
private static final Logger logger = LoggerFactory.getLogger(CardsController.class);

	@PostMapping("/myCards")
	public List<Cards> getCardDetails(@RequestBody Customer customer) {
//		showing the log
		logger.info("Inside the cards service");

		List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());

		logger.info("method ended");
		if (cards != null) {
			return cards;
		} else {
			return null;
		}
	}

	@GetMapping("/cardsTest")
	public String justTest(){
		return "cards test";
	}

}
