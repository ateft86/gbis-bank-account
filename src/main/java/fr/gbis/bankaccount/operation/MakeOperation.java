package fr.gbis.bankaccount.operation;

import fr.gbis.bankaccount.model.Account;
import fr.gbis.bankaccount.model.Amount;
import fr.gbis.bankaccount.model.CurrencyChangeRate;
import fr.gbis.bankaccount.operation.Operation.OperationType;

import java.time.Clock;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

public abstract class MakeOperation {

	protected final Clock clock;
	protected final CurrencyChangeRate currencyChangeRate;

	public MakeOperation(final Clock clock, final CurrencyChangeRate currencyChangeRate) {
		this.clock = clock;
		this.currencyChangeRate = currencyChangeRate;
	}

	public Account make(final Account account, final Amount amount, final String name) {

		var accountCurrency = account.currency;
		var amountInAccountCurrency = amount.convertTo(accountCurrency, currencyChangeRate);

		var operation = new Operation(getOperationType(), amountInAccountCurrency, name, Instant.now(clock));

		var operations = new HashSet<>(Optional.ofNullable(account.operations).orElseGet(Collections::emptySet));

		operations.add(operation);

		return new Account(account.iban, account.name, account.currency, operations);
	}

	public abstract OperationType getOperationType();

}
