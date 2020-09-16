package testing.example.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BankAccountTest {

	private static final double AMOUNT = 3;

	private static final double INITIAL_BALANCE = 10;

	@Test
	void testIdIsAutomaticallyAssignedAsPositiveNumber() {
		// setup
		BankAccount bankAccount = new BankAccount();
		// verify
		assertTrue(bankAccount.getId() > 0, "Id should be positive");
	}

	@Test
	void testIdsAreIncremental() {
		BankAccount firstAccount = new BankAccount();
		BankAccount secondAccount = new BankAccount();
		assertTrue(firstAccount.getId() < secondAccount.getId(),
			() -> 
				"Ids were expected to be incremental, but" +
				firstAccount.getId() + " is not less than " +
				secondAccount.getId());
	}

	@Test
	void testDepositWhenAmountIsCorrectShouldIncreaseBalance() {
		// setup
		BankAccount bankAccount = new BankAccount();
		bankAccount.setBalance(INITIAL_BALANCE);
		// exercise
		bankAccount.deposit(AMOUNT);
		// verify
		assertEquals(INITIAL_BALANCE+AMOUNT, bankAccount.getBalance(), 0);
	}

	@Test
	void testDepositWhenAmountIsNegativeShouldThrow() {
		BankAccount bankAccount = new BankAccount();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
			() -> bankAccount.deposit(-1));
		assertAll(
			() -> assertEquals("Negative amount: -1.0", e.getMessage()),
			() -> assertEquals(0, bankAccount.getBalance(), 0)
		);
	}

	@Test
	void testWithdrawWhenAmountIsNegativeShouldThrow() {
		BankAccount bankAccount = new BankAccount();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
			() -> bankAccount.withdraw(-1));
		assertAll(
			() -> assertEquals("Negative amount: -1.0", e.getMessage()),
			() -> assertEquals(0, bankAccount.getBalance(), 0)
		);
	}

	@Test
	void testWithdrawWhenBalanceIsUnsufficientShouldThrow() {
		BankAccount bankAccount = new BankAccount();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
			() -> bankAccount.withdraw(10));
		assertAll(
			() -> assertEquals("Cannot withdraw 10.0 from 0.0", e.getMessage()),
			() -> assertEquals(0, bankAccount.getBalance(), 0)
		);
	}

	@Test
	void testWithdrawWhenBalanceIsSufficientShouldDecreaseBalance() {
		// setup
		BankAccount bankAccount = new BankAccount();
		bankAccount.setBalance(INITIAL_BALANCE);
		// exercise
		bankAccount.withdraw(AMOUNT); // the method we want to test
		// verify
		assertEquals(INITIAL_BALANCE-AMOUNT, bankAccount.getBalance(), 0);
	}
}
