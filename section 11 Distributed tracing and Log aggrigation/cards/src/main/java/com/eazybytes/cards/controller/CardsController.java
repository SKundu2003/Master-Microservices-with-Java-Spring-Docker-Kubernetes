/**
 * 
 */
package com.eazybytes.cards.controller;

import java.util.List;
import java.util.logging.Logger;

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
	private static final Logger log = Logger.getLogger(String.valueOf(CardsController.class));

	@PostMapping("/myCards")
	public List<Cards> getCardDetails(@RequestBody Customer customer) {
//		showing the log
		log.info("Inside the cards service");

		List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());

		log.info("method ended");
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
