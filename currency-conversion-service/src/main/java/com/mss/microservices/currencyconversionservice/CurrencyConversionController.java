package com.mss.microservices.currencyconversionservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {
	
	
	@Autowired
	private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
	
   	@GetMapping("/currency-converter")
	public String convertCurrency(@RequestParam(value="from") String from,@RequestParam(value="to") String to,
			@RequestParam(value="quantity") BigDecimal quantity,Model model) {
   		CurrencyConvesionBean response = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
   		CurrencyConvesionBean currencyConvesionBean = new CurrencyConvesionBean(response.getId(),from,to
   				,quantity,
				response.getConversionMultiple(),
				quantity.multiply(response.getConversionMultiple()),response.getPort());
   		model.addAttribute("from", from);
   		model.addAttribute("to", to);
   		model.addAttribute("quantity", quantity);
   		model.addAttribute("conversionMultiple", response.getConversionMultiple());
   		model.addAttribute("totalAmount", quantity.multiply(response.getConversionMultiple()));
   		
		return "convertedCurrency";
	}
   	
   	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConvesionBean convertCurrencyFeign(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
   		CurrencyConvesionBean response = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
   		CurrencyConvesionBean currencyConvesionBean = new CurrencyConvesionBean(response.getId(),from,to,quantity,
				response.getConversionMultiple(),
				quantity.multiply(response.getConversionMultiple()),response.getPort());
		return currencyConvesionBean;
	}
	
	
}
