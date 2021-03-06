package com.microservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange" ,url = "localhost:8000")//hardcoded urls
@FeignClient(name = "currency-exchange" )
public interface CurrencyExchangeProxy {
	
	@GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable String from,@PathVariable String to);
	//same method as CurrencyExchange but wth CurrencyConvserion as return type
	
}
