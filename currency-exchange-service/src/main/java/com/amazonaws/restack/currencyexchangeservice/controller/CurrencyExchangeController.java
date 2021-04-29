package com.amazonaws.restack.currencyexchangeservice.controller;

import com.amazonaws.restack.currencyexchangeservice.model.CurrencyExchange;
import com.amazonaws.restack.currencyexchangeservice.model.CurrencyExchangeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CurrencyExchangeController {
  @Autowired private Environment environment;
  @Autowired private CurrencyExchangeRepository repository;

  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to) {
    log.info(String.format("Currency Exchange called with %s to %s", from, to));

    CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
    if (currencyExchange == null) {
      throw new RuntimeException(
          String.format("Unable to find the data to convert %s to %s", from, to));
    }
    String port = environment.getProperty("local.server.port");
    String host = environment.getProperty("HOSTNAME");
    String version = "v1";
    currencyExchange.setEnvironment(String.format("%s %s %s", port, version, host));

    return currencyExchange;
  }
}
