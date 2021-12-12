package fr.gbis.bankaccount.model;

import fr.gbis.bankaccount.operation.Operation;

import java.util.Objects;

public final class History {

	public final Amount balance;
	public final Operation operation;

	public History(final Amount balance, final Operation operation) {
		this.balance = balance;
		this.operation = operation;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof History)) {
			return false;
		}
		History history = (History) o;
		return Objects.equals(balance, history.balance) && Objects.equals(operation, history.operation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, operation);
	}

	@Override
	public String toString() {
		return "History{" + "balance=" + balance + ", operation=" + operation + '}';
	}
}
