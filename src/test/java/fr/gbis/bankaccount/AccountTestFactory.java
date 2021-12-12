package fr.gbis.bankaccount;

import fr.gbis.bankaccount.model.Account;
import fr.gbis.bankaccount.model.Amount;
import fr.gbis.bankaccount.model.IBAN;
import fr.gbis.bankaccount.operation.Operation;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Currency;
import java.util.Set;

import static fr.gbis.bankaccount.operation.Operation.OperationType.DEPOSIT;

public class AccountTestFactory {

	public static final Currency USD = Currency.getInstance("USD");
	public static final Currency EUR = Currency.getInstance("EUR");

	private AccountTestFactory() {
		// Do nothing
	}

	public static Account an_account_in_euros_with_a_first_deposit_of_ten_euros() {
		return new Account(new IBAN("FR", "76", "30002007841544567890772"), "Test Bank Account",
				Currency.getInstance("EUR"), Set.of(new Operation(DEPOSIT, new Amount(new BigDecimal(10), EUR),
						"First deposit", Instant.now().minus(1, ChronoUnit.DAYS))));
	}

}
