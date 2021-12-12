package fr.gbis.bankaccount.operation;

import fr.gbis.bankaccount.model.CurrencyChangeRate;
import fr.gbis.bankaccount.operation.Operation.OperationType;

import java.time.Clock;

import static fr.gbis.bankaccount.operation.Operation.OperationType.DEPOSIT;

public final class MakeDeposit extends MakeOperation {

	public MakeDeposit(final Clock clock, final CurrencyChangeRate currencyChangeRate) {
		super(clock, currencyChangeRate);
	}

	@Override
	public OperationType getOperationType() {
		return DEPOSIT;
	}
}
