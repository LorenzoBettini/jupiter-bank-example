package testing.example.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Bank {

	private List<BankAccount> bankAccounts = new ArrayList<>();

	/**
	 * Package-private, for internal use only, for example, for testing.
	 */
	List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public int openNewBankAccount(double initialBalance) {
		BankAccount newBankAccount = new BankAccount();
		newBankAccount.setBalance(initialBalance);
		bankAccounts.add(newBankAccount);
		return newBankAccount.getId();
	}

	public void deposit(int bankAccountId, double amount) {
		findBankAccountById(bankAccountId).deposit(amount);
	}

	public void withdraw(int bankAccountId, double amount) {
		findBankAccountById(bankAccountId).withdraw(amount);
	}

	private BankAccount findBankAccountById(int bankAccountId) {
		return bankAccounts.stream()
			.filter(a -> a.getId() == bankAccountId)
			.findFirst()
			.orElseThrow(
				() -> new NoSuchElementException
					("No account found with id: " + bankAccountId));
	}
}