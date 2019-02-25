package testing.example.bank;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BankAccountTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

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

	@Test
	public void testDepositWhenAmountIsNegativeShouldThrow() {
		// setup
		BankAccount bankAccount = new BankAccount();
		try {
			// exercise
			bankAccount.deposit(-1);
			fail("Expected an IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			// verify
			assertEquals("Negative amount: -1.0", e.getMessage());
			assertEquals(0, bankAccount.getBalance(), 0);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDepositWhenAmountIsNegativeShouldThrowWithExpected() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.deposit(-1);
	}

	@Test
	public void testDepositWhenAmountIsNegativeShouldThrowAlternative() {
		BankAccount bankAccount = new BankAccount();
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Negative amount: -1.0");
		bankAccount.deposit(-1);
		// but we can't perform further assertions...
	}
}
