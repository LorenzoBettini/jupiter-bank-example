package testing.example.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Tests for Bank")
class BankNestedExampleTest {

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

	@Nested
	@DisplayName("Happy cases")
	class HappyCases {
		@Test
		@DisplayName("Open a new bank account")
		void testOpenNewAccountShouldReturnAPositiveIdAndStoreTheAccount() {
			int newAccountId = bank.openNewBankAccount(0);
			assertAll(
				() -> assertTrue(newAccountId > 0, "Unexpected non positive id: " + newAccountId),
				() -> assertEquals(newAccountId, bankAccounts.get(0).getId())
			);
		}

		@Test
		@DisplayName("Increment balance with 'deposit'")
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
		@DisplayName("Decrement balance with 'withdraw'")
		void testWithdrawWhenAccountIsFoundShouldDecrementBalance() {
			// setup
			BankAccount testAccount = createTestAccount(INITIAL_BALANCE);
			bankAccounts.add(testAccount);
			// exercise
			bank.withdraw(testAccount.getId(), AMOUNT);
			// verify
			assertEquals(INITIAL_BALANCE-AMOUNT, testAccount.getBalance(), 0);
		}

	}

	@Nested
	@DisplayName("Error cases")
	class ExceptionalCases {
		@Test
		@DisplayName("Account for 'deposit' not found")
		void testDepositWhenAccountIsNotFoundShouldThrow() {
			assertThrows(NoSuchElementException.class,
				() -> bank.deposit(1, INITIAL_BALANCE));
		}

		@Test
		@DisplayName("Account for 'withdraw' not found")
		void testWithdrawWhenAccountIsNotFoundShouldThrow() {
			assertThrows(NoSuchElementException.class,
				() -> bank.withdraw(1, AMOUNT));
		}
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
