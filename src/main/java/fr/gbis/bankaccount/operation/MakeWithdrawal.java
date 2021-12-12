package fr.gbis.bankaccount.operation;

import fr.gbis.bankaccount.model.Account;
import fr.gbis.bankaccount.model.Amount;
import fr.gbis.bankaccount.model.CurrencyChangeRate;
import fr.gbis.bankaccount.operation.Operation.OperationType;

import java.math.BigDecimal;
import java.time.Clock;

import static fr.gbis.bankaccount.operation.Operation.OperationType.WITHDRAWAL;

public final class MakeWithdrawal extends MakeOperation {

	public MakeWithdrawal(final Clock clock, final CurrencyChangeRate currencyChangeRate) {
		super(clock, currencyChangeRate);
	}

	@Override
	public Account make(final Account account, final Amount amount, final String name) {

		if (isAccountBalanceWillBeNegativeAfterWithdrawal(account, amount)) {
			throw new UnsupportedOperationException("Cannot withdraw money you don't have !");
		}

		return super.make(account, amount, name);
	}

	private boolean isAccountBalanceWillBeNegativeAfterWithdrawal(final Account account, final Amount amount) {
		var amountInAccountCurrency = amount.convertTo(account.currency, super.currencyChangeRate);
		var accountBalance = account.getBalance();

		var newPotentialAccountBalanceAfterWithdraw = accountBalance.subtract(amountInAccountCurrency);

		return newPotentialAccountBalanceAfterWithdraw.amount.compareTo(new BigDecimal(0)) < 0;
	}

	@Override
	public OperationType getOperationType() {
		return WITHDRAWAL;
	}
}
