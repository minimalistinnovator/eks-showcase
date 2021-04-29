package com.amazonaws.restack.currencyconversionservice.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversion {
  private Long id;
  private String from;
  private String to;
  private BigDecimal quantity;
  private BigDecimal conversionMultiple;
  private BigDecimal totalCalculatedAmount;
  private String environment;
}
