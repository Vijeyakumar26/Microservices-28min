package com.microservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment environment; //Spring class to get port number
	
	@GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from,@PathVariable String to)
	{
		//CurrencyExchange currencyExchange = new CurrencyExchange(1000L, "USD", "INR", BigDecimal.valueOf(50));
		
		logger.info("retrieve Exchange values {} and {}",from,to);		
		
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);//from JPA repository
		
		if(currencyExchange == null)
		{
			throw new RuntimeException("Unable to find the data from "+from+" to "+to);	//if no data found
		}
		
		String property = environment.getProperty("local.server.port");	//gets port number
		
		currencyExchange.setEnvironment(property);
		
		return currencyExchange;
	}
}
