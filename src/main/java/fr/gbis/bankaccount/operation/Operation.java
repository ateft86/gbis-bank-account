package fr.gbis.bankaccount.operation;

import fr.gbis.bankaccount.model.Amount;

import java.time.Instant;
import java.util.Objects;

public final class Operation {

	public final OperationType operationType;
	public final Amount amount;
	public final String name;
	public final Instant date;

	public Operation(final OperationType operationType, final Amount amount, final String name, final Instant date) {
		this.operationType = operationType;
		this.amount = amount;
		this.name = name;
		this.date = date;
	}

	public Amount calculNewBalance(final Amount currentBalance) {
		return switch (operationType) {
		case DEPOSIT -> currentBalance.add(amount);
		case WITHDRAWAL -> currentBalance.subtract(amount);
		};
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Operation)) {
			return false;
		}
		Operation operation = (Operation) o;
		return operationType == operation.operationType && Objects.equals(amount, operation.amount)
				&& Objects.equals(name, operation.name) && Objects.equals(date, operation.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(operationType, amount, name, date);
	}

	@Override
	public String toString() {
		return "Operation{" + "operationType=" + operationType + ", amount=" + amount + ", name='" + name + '\''
				+ ", date=" + date + '}';
	}

	public enum OperationType {
		DEPOSIT, WITHDRAWAL
	}

}
