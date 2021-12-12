package fr.gbis.bankaccount.model;

import java.math.BigDecimal;
import java.util.Currency;

@FunctionalInterface
public interface CurrencyChangeRate {

	BigDecimal getRate(final Currency from, final Currency to);

}
