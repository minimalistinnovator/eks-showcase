package com.amazonaws.restack.currencyconversionservice.controller;

import com.amazonaws.restack.currencyconversionservice.model.CurrencyConversion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

@RestController
@Slf4j
public class CurrencyConversionController {
  @Autowired private CurrencyExchangeProxy currencyExchangeProxy;
  private DecimalFormat decimalFormat =
      (DecimalFormat) NumberFormat.getNumberInstance(Locale.getDefault());

  @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversion(
      @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

    log.info(
        String.format(
            "Calculate currency conversion called with %s to %s for %.2f quantity",
            from, to, new Double(decimalFormat.format(quantity))));
    CurrencyConversion exchangeValue = currencyExchangeProxy.getExchangeValue(from, to);
    return new CurrencyConversion(
        exchangeValue.getId(),
        from,
        to,
        quantity,
        exchangeValue.getConversionMultiple(),
        quantity.multiply(exchangeValue.getConversionMultiple()),
        exchangeValue.getEnvironment());
  }
}
