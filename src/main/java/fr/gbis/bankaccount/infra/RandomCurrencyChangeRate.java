package fr.gbis.bankaccount.infra;

import fr.gbis.bankaccount.model.CurrencyChangeRate;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Double.MAX_VALUE;

public final class RandomCurrencyChangeRate implements CurrencyChangeRate {

	@Override
	public BigDecimal getRate(final Currency from, final Currency to) {

		if (from.equals(to)) {
			return new BigDecimal(1);
		}

		return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0, MAX_VALUE));
	}
}
