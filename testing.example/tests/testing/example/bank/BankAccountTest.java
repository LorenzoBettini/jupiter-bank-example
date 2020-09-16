package testing.example.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

	private static final double AMOUNT = 3;

	private static final double INITIAL_BALANCE = 10;

	@Test
	public void testIdIsAutomaticallyAssignedAsPositiveNumber() {
		// setup
		BankAccount bankAccount = new BankAccount();
		// verify
		assertTrue(bankAccount.getId() > 0, "Id should be positive");
	}

	@Test
	public void testIdsAreIncremental() {
		BankAccount firstAccount = new BankAccount();
		BankAccount secondAccount = new BankAccount();
		assertTrue(firstAccount.getId() < secondAccount.getId(),
			"Ids should be incremental");
	}

	@Test
	public void testDepositWhenAmountIsCorrectShouldIncreaseBalance() {
		// setup
		BankAccount bankAccount = new BankAccount();
		bankAccount.setBalance(INITIAL_BALANCE);
		// exercise
		bankAccount.deposit(AMOUNT);
		// verify
		assertEquals(INITIAL_BALANCE+AMOUNT, bankAccount.getBalance(), 0);
	}

	@Test
	public void testDepositWhenAmountIsNegativeShouldThrow() {
		BankAccount bankAccount = new BankAccount();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
			() -> bankAccount.deposit(-1));
		// perform assertions on the thrown exception
		assertEquals("Negative amount: -1.0", e.getMessage());
		// and we can perform further assertions...
		assertEquals(0, bankAccount.getBalance(), 0);
	}

	@Test
	public void testWithdrawWhenAmountIsNegativeShouldThrow() {
		BankAccount bankAccount = new BankAccount();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
			() -> bankAccount.withdraw(-1));
		assertEquals("Negative amount: -1.0", e.getMessage());
		assertEquals(0, bankAccount.getBalance(), 0);
	}

	@Test
	public void testWithdrawWhenBalanceIsUnsufficientShouldThrow() {
		BankAccount bankAccount = new BankAccount();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
			() -> bankAccount.withdraw(10));
		assertEquals("Cannot withdraw 10.0 from 0.0", e.getMessage());
		assertEquals(0, bankAccount.getBalance(), 0);
	}

	@Test
	public void testWithdrawWhenBalanceIsSufficientShouldDecreaseBalance() {
		// setup
		BankAccount bankAccount = new BankAccount();
		bankAccount.setBalance(INITIAL_BALANCE);
		// exercise
		bankAccount.withdraw(AMOUNT); // the method we want to test
		// verify
		assertEquals(INITIAL_BALANCE-AMOUNT, bankAccount.getBalance(), 0);
	}
}
