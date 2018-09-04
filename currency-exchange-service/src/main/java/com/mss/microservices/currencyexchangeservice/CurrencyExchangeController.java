package com.mss.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepo;
    
	//@CrossOrigin(origins = "http://localhost:8100")
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from,@PathVariable String to) {
		
		
    	ExchangeValue exchangeValue = exchangeValueRepo.findByFromAndTo(from, to);
    	//exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
    	return exchangeValue;
    }
}
