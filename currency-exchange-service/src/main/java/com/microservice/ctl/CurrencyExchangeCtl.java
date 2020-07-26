package com.microservice.ctl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.Dao.ExchangeValueRepository;
import com.microservice.dto.ExchangeValue;

@RestController
public class CurrencyExchangeCtl 
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment enviroment;
	
	@Autowired
	ExchangeValueRepository repo;
	
	@GetMapping("/currency-exchange-service/from/{from}/to/{to}")
	public ExchangeValue getValue(@PathVariable String from,@PathVariable String to)
	{
//		ExchangeValue currencyValue = 
//					new ExchangeValue(1, from, to, BigDecimal.valueOf(65));
		
		ExchangeValue exchangeValue = repo.findByFromAndTo(from, to);
//		
		exchangeValue.setPort(
				Integer.parseInt(enviroment.getProperty("local.server.port")));
		
		logger.info("{}",exchangeValue);
		
		return exchangeValue;
	}
}