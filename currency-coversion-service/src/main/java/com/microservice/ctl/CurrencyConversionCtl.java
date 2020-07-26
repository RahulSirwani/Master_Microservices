package com.microservice.ctl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.dto.CurrencyConversionBean;
import com.microservice.service.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionCtl 
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from ,
			@PathVariable String to , @PathVariable BigDecimal quantity)
	{
		System.out.println("----------------");
		Map<String, String> uriVariable = new HashMap<String, String>();
		uriVariable.put("from",from);
		uriVariable.put("to",to);
		
		ResponseEntity<CurrencyConversionBean> response =  new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange-service/from/{from}/to/{to}",
				CurrencyConversionBean.class ,
				uriVariable);
		
		CurrencyConversionBean bean = response.getBody();
		
		
		return new CurrencyConversionBean(bean.getId(), from, to,bean.getConversionMultiple() ,
				quantity, quantity.multiply(bean.getConversionMultiple()),bean.getPort());
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from ,
			@PathVariable String to , @PathVariable BigDecimal quantity)
	{
		
		CurrencyConversionBean bean = proxy.getValue(from, to);
		
		logger.info("{----}",bean);
		return new CurrencyConversionBean(bean.getId(), from, to,bean.getConversionMultiple() ,
				quantity, quantity.multiply(bean.getConversionMultiple()),bean.getPort());
	}
}
