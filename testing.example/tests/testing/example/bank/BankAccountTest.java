package testing.example.bank;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankAccountTest {

	@Test
	public void testIdIsAutomaticallyAssignedAsPositiveNumber() {
		// setup
		BankAccount bankAccount = new BankAccount();
		// verify
		assertTrue("Id should be positive", bankAccount.getId() > 0);
	}

	@Test
	public void testIdsAreIncremental() {
		assertTrue("Ids should be incremental",
			new BankAccount().getId() < new BankAccount().getId());
	}

	// WRONG VERSION!
	// Works only if this is the first executed test
//	@Test
//	public void testIdsAreIncrementalWrong() {
//		assertEquals(1, new BankAccount().getId());
//		assertEquals(2, new BankAccount().getId());
//	}

	@Test
	public void testDepositWhenAmountIsCorrectShouldIncreaseBalance() {
		// setup
		BankAccount bankAccount = new BankAccount();
		// exercise
		bankAccount.deposit(10);
		// verify
		assertEquals(10, bankAccount.getBalance(), 0);
	}
}
