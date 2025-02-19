package testing.example.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankTest {

	private static final double AMOUNT = 5;

	private static final double INITIAL_BALANCE = 10;

	private Bank bank;

	// the collaborator of Bank that we manually instrument and inspect
	private List<BankAccount> bankAccounts;

	@BeforeEach
	void setup() {
		bankAccounts = new ArrayList<>();
		bank = new Bank(bankAccounts);
	}

	@Test
	void testOpenNewAccountShouldReturnAPositiveIdAndStoreTheAccount() {
		int newAccountId = bank.openNewBankAccount(0);
		assertAll(
			() -> assertTrue(newAccountId > 0, "Unexpected non positive id: " + newAccountId),
			() -> assertEquals(newAccountId, bankAccounts.get(0).getId())
		);
	}

	@Test
	void testDepositWhenAccountIsNotFoundShouldThrow() {
		assertThrows(NoSuchElementException.class,
			() -> bank.deposit(1, INITIAL_BALANCE));
	}

	@Test
	void testDepositWhenAccountIsFoundShouldIncrementBalance() {
		// setup
		BankAccount testAccount = createTestAccount(INITIAL_BALANCE);
		bankAccounts.add(testAccount);
		// exercise
		bank.deposit(testAccount.getId(), AMOUNT);
		// verify
		assertEquals(INITIAL_BALANCE+AMOUNT, testAccount.getBalance(), 0);
	}

	@Test
	void testWithdrawWhenAccountIsNotFoundShouldThrow() {
		assertThrows(NoSuchElementException.class,
			() -> bank.withdraw(1, AMOUNT));
	}

	@Test
	void testWithdrawWhenAccountIsFoundShouldDecrementBalance() {
		// setup
		BankAccount testAccount = createTestAccount(INITIAL_BALANCE);
		bankAccounts.add(testAccount);
		// exercise
		bank.withdraw(testAccount.getId(), AMOUNT);
		// verify
		assertEquals(INITIAL_BALANCE-AMOUNT, testAccount.getBalance(), 0);
	}

	/**
	 * Utility method for creating a BankAccount for testing.
	 */
	private BankAccount createTestAccount(double initialBalance) {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setBalance(initialBalance);
		return bankAccount;
	}
}
