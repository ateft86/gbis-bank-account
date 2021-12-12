package fr.gbis.bankaccount.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public final class Amount {

	public final BigDecimal amount;
	public final Currency currency;

	public Amount(final BigDecimal amount, final Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public Amount add(final Amount amountToAdd) {
		assertThatAmountHaveSameCurrency(amountToAdd);
		return new Amount(amount.add(amountToAdd.amount), currency);
	}

	public Amount subtract(final Amount amountToMin) {
		assertThatAmountHaveSameCurrency(amountToMin);
		return new Amount(amount.subtract(amountToMin.amount), currency);
	}

	private void assertThatAmountHaveSameCurrency(final Amount amount) {
		if (!currency.equals(amount.currency)) {
			throw new IllegalArgumentException("Amounts must have the same currency !");
		}
	}

	public Amount convertTo(final Currency newCurrency, final CurrencyChangeRate currencyChangeRate) {
		var changeRate = currencyChangeRate.getRate(currency, newCurrency);
		var amountConverted = amount.multiply(changeRate);
		return new Amount(amountConverted, newCurrency);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Amount)) {
			return false;
		}
		Amount amount1 = (Amount) o;
		return Objects.equals(amount, amount1.amount) && Objects.equals(currency, amount1.currency);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, currency);
	}

	@Override
	public String toString() {
		return "Amount{" + "amount=" + amount + ", currency=" + currency + '}';
	}
}
